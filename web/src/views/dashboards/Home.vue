<template>
  <div class="flex flex-col w-full h-full p-2 gap-2">
    <div class="flex flex-col w-full ">
      <p>Constructions</p>
      <div class="flex gap-2">

        <div v-for="construction in constructions" class="flex flex-col gap-4 rounded-xl bg-background-800 w-48 p-2">
          <p>{{ construction.name }}</p>

          <div class="flex rounded-xl overflow-hidden h-4">
            <div class="bg-primary-600"
                 :style="{ width: (construction.total / construction.stageBudget) * 100  + '%' }"></div>
            <div class="flex-1 bg-accent-800"></div>
          </div>
        </div>

      </div>
    </div>

  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import data from "../../services/data.js";
import {useUserStore} from "../../store/userStore.js";

const user = useUserStore();
let constructions = ref([]);

onMounted(async () => {
  if (user.userType.type.toLowerCase() === "client"){
    constructions.value = await data.getClientConstructions(user.id);
    console.log(constructions.value);
  }
});

</script>

<style scoped>

</style>