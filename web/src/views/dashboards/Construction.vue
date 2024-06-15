<template>
  <div class="flex justify-between w-full h-fit px-4 py-2">
    <input type="text" class="bg-background-800"/>

    <div class="flex gap-2" v-if="user.userType.type.toLowerCase() === 'engineer'">
      <button class="primary bg-red-600">Delete Construction</button>
      <button class="primary">Edit Construction</button>
      <button class="primary">Add Construction</button>
    </div>
  </div>
  <div class="flex h-fit w-full p-4 mb-6 overflow-x-auto">
    <table class="w-full">
      <thead>
        <tr>
          <th>Engineer</th>
          <th>Stage</th>
          <th>State</th>
          <th>Budget</th>
          <th>Total</th>
          <th>last update</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="construction in constructions" :key="construction.id">
          <td>{{ construction.project.engineer.name }}</td>
          <td>{{ construction.stage.name }}</td>
          <td>{{ construction.state.description }}</td>
          <td>{{ construction.stageBudget }}</td>
          <td>{{ construction.total }}</td>
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
    console.log(constructions.value);
  }
});

const formatDate = (dateString) => {
  const date = new Date(dateString);
  return format(date, 'dd/MM/yyyy HH:mm');
};
</script>

<style scoped>

</style>