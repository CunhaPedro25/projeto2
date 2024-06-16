<template>
  <div class="h-screen flex flex-col relative bg-background-800 z-50 w-20 text-white">
    <div class="flex w-full px-4 py-6 object-center object-contain  items-center overflow-hidden gap-0">
      <img src="@/assets/icons/ecobuild.svg" alt="Eco build icon"/>
    </div>
    <div class="flex flex-col w-full h-full items-center gap-2 text-2xl overflow-y-auto overflow-x-hidden" style="scrollbar-width: thin;">
      <SidebarItem v-for="item in sidebarItems" :icon="item.icon" :path="item.page">{{item.name}}</SidebarItem>
    </div>
    <div class="flex w-full justify-center p-2">
      <SidebarItem class="!text-red-600 hover:bg-red-600 hover:!text-white" icon="logout" path="/">Log-out</SidebarItem>
    </div>
  </div>
</template>

<script setup>
import SidebarItem from './SidebarItem.vue'
import {createItems} from '../../utils/sidebarItemFactory.js';
import {useUserStore} from "@/store/userStore";
import Cookies from "js-cookie";

const userStore = useUserStore();
let sidebarItems = createItems(userStore.userType.type.toLowerCase());
const type = Cookies.get("user_type")
const leader = Cookies.get("team_leader")
if (type === "worker" && leader !== "true") {
  sidebarItems.pop()
}
</script>
