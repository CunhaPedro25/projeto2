<template>
  <div class="flex justify-between w-full h-fit px-4 py-2">
    <div>
      <p>Search:</p>
      <input type="text" class="bg-background-800"/>
    </div>

  </div>
  <div class="flex h-fit w-full p-4 mb-6 overflow-x-auto">
    <table class="w-full" v-if="constructions.length > 0">
      <thead>
        <tr>
          <th>Name</th>
          <th>Engineer</th>
          <th>Stage</th>
          <th>State</th>
          <th>Total</th>
          <th>last update</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="construction in constructions">
          <td>{{ construction.name }}</td>
          <td>{{ construction.project.engineer.name }}</td>
          <td>{{ construction.stage.name }}</td>
          <td>{{ construction.state.description }}</td>
          <td>
            <div class="flex items-center gap-2">
              <div class="flex rounded-xl overflow-hidden h-4 flex-1 min-w-20 max-w-20">
                <div class="bg-primary-600 relative"
                     :style="{ width: (construction.total / construction.stageBudget) * 100  + '%' }">
                </div>
                <div class="flex-1 bg-accent-800"></div>
              </div>

              <p class="text-xs">{{ (construction.total / construction.stageBudget) * 100  }}% / {{ construction.stageBudget }}€</p>
            </div>
          </td>
          <td>{{ formatDate(construction.lastUpdate) }}</td>
          <td>
            <RouterLink :to="`/dashboard/construction/${construction.id}`" class="button primary">View</RouterLink>
          </td>
        </tr>
      </tbody>
    </table>

    <p class="text-center text-text-300" v-else>No constructions found...</p>
  </div>
</template>

<script setup>
import {useUserStore} from "../../store/userStore.js";
import {onMounted, ref} from "vue";
import data from "../../services/data.js"
import { format } from 'date-fns';
import Cookies from "js-cookie";

const id = Cookies.get("user_id");
const type = Cookies.get("user_type");
let constructions = ref([]);

onMounted(async () => {
  if (type === "client"){
    constructions.value = await data.getClientConstructions(id);
  }else if (type === "worker") {
    const constructionsTeams = await data.getTeamConstructions(Cookies.get("team_id"))
    constructionsTeams.forEach(constructionTeam => {
      if (!constructions.value.some(construction => construction.id === constructionTeam.construction.id)) {
        constructions.value.push(constructionTeam.construction);
      }
    });
  }else if (type === "engineer"){
    constructions.value = await data.getEngineerConstructions(id);
  }
});

const formatDate = (dateString) => {
  const date = new Date(dateString);
  return format(date, 'dd/MM/yyyy HH:mm');
};
</script>

<style scoped>

</style>