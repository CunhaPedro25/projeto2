<template>
  <div class="flex justify-between w-full h-fit px-4 py-2">
    <div>
      <p>Search:</p>
      <input type="text" class="bg-background-800"/>
    </div>
  </div>
  <div class="flex h-fit w-full p-4 mb-6 overflow-x-auto">
    <table class="w-full" v-if="budgets.length > 0">
      <thead>
      <tr>
        <th>Engineer</th>
        <th>Type</th>
        <th>Create Date</th>
        <th>State</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="budget in budgets" :key="budget.id">
        <td>{{ budget.engineer.name }}</td>
        <td>{{ budget.constructionType.type }}</td>
        <td>{{ date.formatDate(budget.budgetCreateDate) }}</td>
        <td>{{ budget.budgetState === null ? "Pending" : budget.budgetState ? "Approved" : "Rejected" }}</td>
        <td>
          <div class="flex gap-2" v-if="budget.budgetState === null">
            <button class="primary" @click="updateBudgetState(budget.id, true)">
              <span class="material-icons">check</span>
            </button>
            <button class="primary !bg-red-600" @click="updateBudgetState(budget.id, false)">
              <span class="material-icons">close</span>
            </button>
          </div>

          <button class="primary" v-if="budget.budgetState !== null">
            <span class="material-icons">file_open</span>
          </button>
        </td>
      </tr>
      </tbody>
    </table>

    <p class="text-center text-text-300" v-else>No budgets found...</p>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import data from "../../services/data.js";
import date from "@/utils/date";
import '@material-design-icons/font';
import Cookies from "js-cookie";

const id = Cookies.get("user_id");
const type = Cookies.get("user_type");
let budgets = ref([]);

const fetchBudgets = async () => {
  if (type === "client") {
    budgets.value = await data.getClientProjects(id);
    budgets.value = budgets.value.filter(budget => budget.budget !== null);
  }
};

onMounted(async () => {
  await fetchBudgets();
});

const updateBudgetState = async (id, state) => {
  await data.updateBudgetState(id, state);
  await fetchBudgets();
};
</script>

<style scoped>

</style>