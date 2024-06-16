<template>
  <div v-if="construction !== null" class="flex flex-col gap-2 px-6">
    <div class="flex justify-between w-full">
      <div class="flex items-end gap-2 text-text-300">
        <p class="text-white text-4xl">{{ construction.name }}</p>
        <p>{{ construction.stage.constructionType.type }}</p>
        <p>{{ date.formatDate(construction.lastUpdate) }}</p>
        <p>{{ construction.state.description }}</p>
      </div>

      <button class="primary">
        Write complaint
      </button>
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


    <div class="flex h-fit w-full p-4 mb-6 overflow-x-auto">
      <table class="w-full">
        <thead>
        <tr>
          <th>Equipa</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Days</th>
          <th>Daily Value</th>
          <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="constructionTeam in constructionTeams" :key="construction.id">
          <td>{{ constructionTeam.team.id }}</td>
<!--          <td>{{ date.formatDate(constructionTeam.startDate) }}</td>-->
<!--          <td>{{ date.formatDate(constructionTeam.endDate) }}</td>-->
          <td>{{ constructionTeam.days }}</td>
          <td>{{ constructionTeam.dailyValue }}</td>
          <td>{{ constructionTeam.days * constructionTeam.dailyValue }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import data from "../../services/data.js"
import date from "../../utils/date.js";

let construction = ref(null);
let constructionTeams = ref([]);
const route = useRoute();

onMounted(async () => {
  const id = route.params.id;
  if (user.userType.type.toLowerCase() === "client") {
    construction.value = await data.getConstruction(id);
    constructionTeams.value = await data.getConstructionTeamConstruction(id);
  } else if (user.userType.type.toLowerCase() === "worker") {
    construction.value = await data.getConstruction(id);
    constructionTeams.value = await data.getConstructionTeamTeam(id, user.team.id);
  }
});
</script>

<style scoped>

</style>