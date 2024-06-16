<template>
  <div class="flex justify-between w-full h-fit px-4 py-2">
    <div>
      <p>Search:</p>
      <input type="text" class="bg-background-800"/>
    </div>

    <div class="flex gap-2 items-end" v-if="user.userType.type.toLowerCase() === 'engineer'">
      <button class="primary !bg-red-600">Delete Construction</button>
      <button class="primary">Edit Construction</button>
      <button class="primary">Add Construction</button>
    </div>
  </div>
  <div class="flex h-fit w-full p-4 mb-6 overflow-x-auto">
    <table class="w-full">
      <thead>
        <tr>
          <th>Name</th>
          <th>Engineer</th>
          <th>Stage</th>
          <th>State</th>
          <th>Total</th>
          <th>last update</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="construction in constructions" :key="construction.id">
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
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import {useUserStore} from "../../store/userStore.js";
import {onMounted, ref} from "vue";
import data from "../../services/data.js"
import { format } from 'date-fns';

const user = useUserStore();
let constructions = ref([]);

onMounted(async () => {
  if (user.userType.type.toLowerCase() === "client"){
    constructions.value = await data.getClientConstructions(user.id);
  }else if (user.userType.type.toLowerCase() === "worker") {
    constructions.value = await data.getTeamConstructions(user.team.id);
  }
});

const formatDate = (dateString) => {
  const date = new Date(dateString);
  return format(date, 'dd/MM/yyyy HH:mm');
};
</script>

<style scoped>

</style>