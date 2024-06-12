<template>
  <div class="h-screen flex flex-col relative bg-gray-200 transition-[width] shadow-xl z-50 w-20">
    <div class="flex bg-gray-200 border-b-2 border-gray-300 h-16 items-center px-8 py-4 overflow-hidden gap-0 w-20">

    </div>
    <div class="flex flex-col w-full h-full text-2xl overflow-y-auto overflow-x-hidden" style="scrollbar-width: thin;">
      <SidebarItem v-for="item in sidebarItems" :icon="item.icon" :path="item.page">{{item.name}}</SidebarItem>
    </div>
    <div>
      <SidebarItem class="border-t-2" icon="sign-out-alt" path="/">Log-out</SidebarItem>
    </div>
  </div>
</template>

<script setup>
import SidebarItem from './SidebarItem.vue'
import { createItems } from '../utils/sidebarItemFactory';
import {useUserStore} from "@/store/userStore";

const userStore = useUserStore();
const sidebarItems = createItems(userStore.userType.type.toLowerCase());
</script>
