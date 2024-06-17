<template>
  <div class="flex justify-between w-full h-fit px-4 py-2">
    <div>
      <p>Search:</p>
      <input type="text" class="bg-background-800"/>
    </div>

    <div class="flex gap-2 items-end" v-if="type === 'client'">
      <button class="primary" @click="$refs.addProject.show()">Add Project</button>
    </div>
  </div>
  <div class="flex h-fit w-full p-4 mb-6 overflow-x-auto">
    <table class="w-full" v-if="projects.length > 0">
      <thead>
      <tr>
        <th>Engineer</th>
        <th>Type</th>
        <th>Create Date</th>
        <th>State</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="project in projects" :key="project.id">
        <td>{{ project.engineer !== null ? project.engineer.name : "Pending" }}</td>
        <td>{{ project.constructionType.type }}</td>
        <td>{{ date.formatDate(project.requirementsCreateDate) }}</td>
        <td>{{ project.requirementsState === null ? "Pending" : project.requirementsState ? "Approved" : "Rejected" }}</td>

        <td>
          <div class="flex gap-2" v-if="project.requirementsState === null && type === 'engineer'">
            <button class="primary" @click="updateBudgetState(project.id, true)">
              <span class="material-icons">check</span>
            </button>
            <button class="primary !bg-red-600" @click="updateBudgetState(project.id, false)">
              <span class="material-icons">close</span>
            </button>
          </div>

          <button class="primary" v-else>
            <span class="material-icons">file_open</span>
          </button>
        </td>
      </tr>
      </tbody>
    </table>

    <p class="text-center text-text-300" v-else>No projects found...</p>
  </div>


  <Modal title="Add project" ref="addProject" @open="handleModalOpen" @close="handleModalClose">
    <div class="flex flex-col gap-4 items-center">

      <div class="flex flex-col gap-2 w-full">
        <label for="name">Construction Type</label>
        <select id="constructionType" v-model="constructionType">
          <option v-for="type in types" :value="type">{{ type.type }}</option>
        </select>

        <label for="email">Document</label>
        <input type="file" id="document"/>
      </div>

      <div class="flex gap-1 w-full justify-end">
        <button @click="$refs.addProject.close()">Close</button>
        <button @click="saveProject" class="primary">Save</button>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {onMounted, ref} from "vue";
import data from "../../services/data.js"
import date from "@/utils/date";
import Modal from "../../components/Modal.vue";
import Cookies from "js-cookie";

const id = Cookies.get("user_id");
const type = Cookies.get("user_type");
let projects = ref([]);
let types = ref([]);

const fetchProjects = async () => {
  if (type === "client"){
    projects.value = await data.getClientProjects(id);
    types.value = await data.getAllConstructionTypes();
  }else if (type === "engineer"){
    projects.value = await data.getEngineerProjects(id);
  }
};

onMounted(async () => {
  await fetchProjects();
});

const addProject = ref(null);
const isModalOpen = ref(false);
let constructionType = ref(null);
let document = ref(null);

const updateBudgetState = async (id, state) => {
  await data.updateRequirementsState(id, state);
  await fetchProjects();
};


const handleModalOpen = () => {
  isModalOpen.value = true;
};

const handleModalClose = () => {
  isModalOpen.value = false;
};

const saveProject = async () => {
  await data.saveProject({
    client: user,
    constructionType: constructionType.value
  })
  addProject.value.close();
  await fetchProjects();
};
</script>

<style scoped>

</style>