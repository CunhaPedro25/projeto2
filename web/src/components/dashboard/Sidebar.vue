<template>
  <div class="h-screen flex flex-col relative bg-background-800 z-50 w-20 text-white">
    <div class="flex h-16 items-center px-8 py-4 overflow-hidden gap-0 w-20">
      <p>Eco</p>
    </div>
    <div class="flex flex-col w-full h-full items-center gap-2 text-2xl overflow-y-auto overflow-x-hidden" style="scrollbar-width: thin;">
      <SidebarItem v-for="item in sidebarItems" :icon="item.icon" :path="item.page">{{item.name}}</SidebarItem>
    </div>
    <div class="flex w-full justify-center p-2">
      <SidebarItem class="!text-red-600" icon="logout" path="/">Log-out</SidebarItem>
    </div>
  </div>
</template>

<script setup>
import SidebarItem from './SidebarItem.vue'
import { createItems } from '../../utils/sidebarItemFactory.js';
import {useUserStore} from "@/store/userStore";

const userStore = useUserStore();
const sidebarItems = createItems(userStore.userType.type.toLowerCase());
</script>
