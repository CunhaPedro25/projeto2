<template>
  <RouterLink :to="path === '/' ? path : '/dashboard/' + path"
               class="flex-col gap-4 items-center w-20 min-h-[4rem] border-b-2 border-gray-300 bg-gray-200 hover:bg-gray-300 px-8 py-4 transition-colors cursor-pointer flex justify-start">
    <div class="w-4 h-4 flex items-center justify-center mb-1">
      <span class="material-icons">face</span>
    </div>
    <p class="whitespace-nowrap text-xs">
      <slot></slot>
    </p>
  </RouterLink>
</template>

<script>
import '@material-design-icons/font';

export default {
  name: 'SidebarItem',
  props: [
    "icon",
    "path",
    "Sidebar"
  ],
  data(){
    return{
      selected: false
    }
  }
}
</script>