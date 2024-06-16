<template>
  <div class="flex flex-col w-full h-full px-4 py-2 gap-6">

    <div class="flex flex-col w-full gap-1">
      <p class="text-2xl">Projects</p>
      <div class="flex gap-2 max-w-full overflow-x-auto px-2" v-if="projects.length > 0">
        <router-link to="/dashboard/projects"  v-for="project in projects" class="flex flex-col gap-6 rounded-xl bg-background-800 min-w-56 p-2">
          <div class="flex flex-col gap-1">
            <p>{{ project.constructionType.type }}</p>
            <p class="text-sm text-text-300">{{ date.formatDate(project.requirementsCreateDate) }}</p>
          </div>

          <div class="flex w-full items-end justify-between gap-2">
            <div v-if="type === 'client'">
              <p class="text-text-300">Engineer:</p>
              <p class="text-text-300">{{ project.engineer !== null ? project.engineer.name : "Pending..." }}</p>
            </div>

            <div v-else-if="type === 'engineer'">
              <p class="text-text-300">Client:</p>
              <p class="text-text-300">{{ project.client !== null ? project.client.name : "Pending..." }}</p>
            </div>

            <p class="p-1 rounded-xl"
               :class="[project.requirementsState === null ? 'bg-yellow-400' : project.requirementsState ? 'bg-green-500' : 'bg-red-600' ]">
              {{ project.requirementsState === null ? "Pending" : project.requirementsState ? "Approved" : "Rejected" }}
            </p>
          </div>

        </router-link>
      </div>

      <p class="text-center text-text-300" v-else>No projects found...</p>
    </div>

    <div class="flex flex-col w-full max-w-full gap-1" v-if="type !== 'engineer'">
      <p class="text-2xl">Budgets</p>
      <div class="flex gap-2 max-w-full overflow-x-auto px-2" v-if="budgets.length > 0">
        <router-link to="/dashboard/budgets"  v-for="budget in budgets" class="flex flex-col gap-6 rounded-xl bg-background-800 min-w-56 p-2">
          <div class="flex flex-col gap-1">
            <p>{{ budget.constructionType.type }}</p>
            <p class="text-sm text-text-300">{{ date.formatDate(budget.budgetCreateDate) }}</p>
          </div>

          <div class="flex w-full justify-between items-end gap-2">
            <div>
              <p class="text-text-300">Budget:</p>
              <p class="text-text-300">{{ budget.budget }}€</p>
            </div>


            <p class="p-1 px-2 rounded-xl"
               :class="[budget.budgetState === null ? 'bg-yellow-400' : budget.budgetState ? 'bg-green-500' : 'bg-red-600' ]">
              {{ budget.budgetState === null ? "Pending" : budget.budgetState ? "Approved" : "Rejected" }}
            </p>
          </div>

        </router-link>
      </div>

      <p class="text-center text-text-300" v-else>No budgets found...</p>
    </div>


    <div class="flex flex-col w-full gap-1">
      <p class="text-2xl">Constructions</p>
      <div class="flex gap-2 max-w-full overflow-x-auto  px-2" v-if="constructions.length > 0">
        <router-link :to="'/dashboard/construction/' + construction.id" v-for="construction in constructions" class="flex flex-col gap-6 rounded-xl bg-background-800 min-w-48 p-2">
          <div class="flex flex-col gap-1">
            <p>{{ construction.name }}</p>
            <div class="flex gap-1 text-sm text-text-300">
              <p>{{ construction.state.type }}</p>
              <p>{{ date.formatDate(construction.lastUpdate) }}</p>
            </div>
          </div>

          <div class="flex items-center gap-2">
            <p class="text-xs">{{ (construction.total / construction.stageBudget) * 100  }}%</p>
            <div class="flex rounded-xl overflow-hidden h-4 flex-1">
              <div class="bg-primary-600 relative"
                   :style="{ width: (construction.total / construction.stageBudget) * 100  + '%' }">
              </div>
              <div class="flex-1 bg-accent-800"></div>
            </div>

            <p class="text-xs">{{ construction.stageBudget }}€</p>
          </div>

        </router-link>
      </div>

      <p class="text-center text-text-300" v-else>No constructions found...</p>
    </div>

  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import data from "../../services/data.js";
import {useUserStore} from "../../store/userStore.js";
import date from "@/utils/date";
import Cookies from "js-cookie";

const user = useUserStore();
const type = Cookies.get("user_type")
let projects = ref([]);
let budgets = ref([]);
let constructions = ref([]);

onMounted(async () => {
  if (type === "client"){
    projects.value = await data.getClientProjects(user.id);
    budgets.value = projects.value.filter(project => project.budget !== null);
    constructions.value = await data.getClientConstructions(user.id);
  }else if (type === "engineer"){
    projects.value = await data.getEngineerProjects(user.id);
    constructions.value = await data.getEngineerConstructions(user.id);
  }
});
</script>

<style scoped>

</style>