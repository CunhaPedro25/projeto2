<template>
  <div v-if="construction !== null" class="flex flex-col gap-4 px-6">
    <div class="flex justify-between w-full items-end">
      <div class="flex flex-col items-start gap-2 text-text-300">
        <p class="text-white text-4xl">{{ construction.name }}</p>

        <div class="flex gap-1">
          <p>{{ construction.stage.constructionType.type }}</p>
          <p>{{ date.formatDate(construction.lastUpdate) }}</p>
          <p>{{ construction.state.description }}</p>
        </div>
      </div>

      <div class="flex gap-2">
        <button class="primary" v-if="type === 'client'">
          Write complaint
        </button>
        <button class="primary" v-if="type === 'engineer'" @click="$refs.addTeam.show()">
          Assign New Team
        </button>
        <button class="primary"
                v-if="type === 'worker' && leader === 'true' || type === 'engineer'"
                @click="$refs.request.show()">
          Request Material
        </button>
      </div>
    </div>

    <div class="flex items-center gap-2 text-xl">
      <p>{{ (construction.total / construction.stageBudget) * 100  }}%</p>
      <div class="flex rounded-xl overflow-hidden h-8 flex-1">
        <div class="bg-primary-600 relative"
             :style="{ width: (construction.total / construction.stageBudget) * 100  + '%' }">
        </div>
        <div class="flex-1 bg-accent-800"></div>
      </div>

      <p>{{ construction.stageBudget }}€</p>
    </div>
    <div class="flex flex-col gap-2 h-fit w-full p-4 mb-2 overflow-x-auto">
      <p class="text-2xl">Teams</p>
      <table class="w-full">
        <thead>
        <tr>
          <th>Equipa</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Days</th>
          <th>Daily Value</th>
          <th>Total</th>
          <th v-if="type === 'engineer'">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="constructionTeam in constructionTeams" :key="construction.id">
          <td>{{ constructionTeam.team.id }}</td>
          <td>{{ date.formatDate(constructionTeam.startDate) }}</td>
          <td>{{ constructionTeam.endDate != null ? date.formatDate(constructionTeam.endDate) : "Working" }}</td>
          <td>{{ constructionTeam.days }}</td>
          <td>{{ constructionTeam.dailyValue }}</td>
          <td>{{ constructionTeam.days * constructionTeam.dailyValue }}</td>
          <td>
            <button class="button primary"
                    v-if="type === 'engineer' && constructionTeam.endDate == null"
                    @click="finishWork(constructionTeam.id)">Finish</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="flex flex-col gap-2 h-fit w-full p-4 mb-2 overflow-x-auto">
      <p class="text-2xl">Stock Requests</p>
      <table class="w-full">
        <thead>
        <tr>
          <th>Material</th>
          <th>Quantity</th>
          <th>Status</th>
        </tr>
        </thead>
        <tbody>
          <tr v-for="request in requests" :key="request.id">
            <td>{{ request.material.name }}</td>
            <td>{{ request.quantity }}</td>
            <td>{{ request.accepted === null ? "Pending" : request.accepted ? "Accepted" : "Rejected" }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <div v-else class="flex flex-col justify-center items-center py-6">
    <p class="text-2xl">You arent currently assigned for a construction</p>
    <p>Talk with your supervisor if you think this is a mistake</p>
  </div>



  <Modal title="Add New Team" ref="addTeam" @open="handleModalOpen" @close="handleModalClose">
    <div class="flex flex-col gap-4 items-center">
      <div class="flex flex-col gap-2 w-full">
        <label for="team">Team</label>
        <select id="team" v-model="selectedTeam">
          <option v-for="team in availableTeams" :value="team">{{ team.id }}</option>
        </select>

        <label for="startDate">Start Date</label>
        <input type="date" id="startDate" v-model="startDate" />
      </div>

      <div class="flex gap-1 w-full justify-end">
        <button @click="$refs.addTeam.close()">Close</button>
        <button @click="assignTeam" class="primary">Add</button>
      </div>
    </div>
  </Modal>

  <Modal title="Request Material" ref="request" @open="handleModalOpen" @close="handleModalClose">
    <div class="flex flex-col gap-4 items-center">
      <div class="flex flex-col gap-2 w-full">
        <label for="team">Construction Team</label>
        <select id="team" v-model="selectConstructionTeam" v-if="type === 'engineer'">
          <option v-for="constructionTeam in workingTeams" :value="constructionTeam">{{ constructionTeam.team.id }}</option>
        </select>
        <input v-model="selectConstructionTeam" v-if="type === 'worker'" disabled>

        <label for="material">Material</label>
        <select id="material" v-model="selectMaterial">
          <option v-for="material in materials" :value="material">{{ material.name }}</option>
        </select>

        <label for="quantity">Quantity</label>
        <input type="number" id="quantity" v-model="quantity" />
      </div>

      <div class="flex gap-1 w-full justify-end">
        <button @click="$refs.request.close()">Close</button>
        <button @click="requestStock" class="primary">Add</button>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import data from "../../services/data.js"
import date from "../../utils/date.js";
import Cookies from "js-cookie";
import Modal from "../../components/Modal.vue";

let construction = ref(null);
let constructionTeams = ref([]);
let workingTeams = ref([]);
const type = Cookies.get("user_type");
const leader = Cookies.get("team_leader");
const route = useRoute();

const isModalOpen = ref(false);
let availableTeams = ref([]);
let selectedTeam = ref(null);
let startDate = ref(new Date());
let addTeam = ref(null);


let requests = ref([]);
let request = ref(null);
let materials = ref([]);
let selectConstructionTeam = ref(null);
let selectMaterial = ref(null);
let quantity = ref(0);

const fetchTeams = async () => {
  availableTeams.value = await data.getFreeTeams();
  availableTeams.value = availableTeams.value.sort((a, b) => a.id - b.id);
};

const fetchMaterial = async () => {
  materials.value = await data.getMaterial();
};

const fetchData = async () => {
  let id = route.params.id;
  if (type === "client" || type === "engineer") {
    construction.value = await data.getConstruction(id);
    constructionTeams.value = await data.getConstructionTeams(id);
    workingTeams.value = await data.getWorkingConstructionTeams(id)
    console.log(workingTeams.value)
  } else if (type === "worker") {
    const team = Cookies.get("team_id");
    if (id === undefined){
      const constructionTeam = await data.getTeamCurrentConstruction(team);
      if(constructionTeam === ""){
        return;
      }
      id = constructionTeam.construction.id;
      selectConstructionTeam.value = constructionTeam.id
    }

    construction.value = await data.getConstruction(id);
    constructionTeams.value = await data.getConstructionTeamByBoth(id, team);
  }

  if (type === "engineer" || (type === "worker" && leader === "true")){
    requests.value = await data.getStockRequests(id);
  }

  await fetchTeams();
  await fetchMaterial();
}

onMounted(async () => {
  await fetchData();
});

const handleModalOpen = () => {
  isModalOpen.value = true;
};

const handleModalClose = () => {
  isModalOpen.value = false;
};

const finishWork = async (id) => {
  await data.finishConstruction(id);
  await fetchData();
};

const assignTeam = async () => {
  await data.addTeamToConstruction({
    construction: construction.value,
    team: selectedTeam.value,
    startDate: startDate.value
  })
  addTeam.value.close();
  await fetchData();
};

const requestStock = async () => {
  await data.requestStock({
    constructionTeam: selectConstructionTeam.value,
    material: selectMaterial.value,
    quantity: quantity.value
  })
  request.value.close();
  await fetchData();
};
</script>

<style scoped>

</style>