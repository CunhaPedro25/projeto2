<template>
  <router-link :to="path"
               class="flex flex-col w-16 min-h-16 text-center rounded-xl p-2 text-primary-400 truncate"
               :class="{ 'bg-primary-500 text-white': isActive }"
               @click="path === '/' ? logout() : ''">

    <div class="flex items-center justify-center">
      <span class="material-icons">{{ icon }}</span>
    </div>

    <p class="whitespace-nowrap text-xs truncate">
      <slot></slot>
    </p>
  </router-link>
</template>

<script setup>
import {useRoute, useRouter} from 'vue-router';
import { computed } from "vue";
import {useUserStore} from "../../store/userStore.js";
import '@material-design-icons/font';

const props = defineProps(['icon', 'path'])
const route = useRoute();
const router = useRouter();

const isActive = computed(() => props.path !== '/' && route.path.includes(props.path));

const logout = async () => {
  await useUserStore().logout();
  await router.push('/login');
};
</script>