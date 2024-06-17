<template>
  <div class="flex max-w-full w-full h-full">
    <Sidebar></Sidebar>
    <div class="flex flex-col h-full w-full text-white truncate">
      <Header />
      <div class="flex flex-col flex-1 h-full">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>

<script setup>
import Sidebar from "@/components/dashboard/Sidebar.vue";
import {useUserStore} from "@/store/userStore";
import Header from "../components/dashboard/Header.vue";

const user = useUserStore()
</script>