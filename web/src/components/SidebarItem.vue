<template>
  <RouterLink :to="path === '/' ? path : '/painel/' + path"
               class="w-full min-h-[4rem] border-b-2 border-gray-300 bg-gray-200 hover:bg-gray-300 px-8 py-4 transition-colors cursor-pointer flex gap-4 justify-start items-center"
               :class="[(Sidebar ? '' : 'flex-col gap-0 items-start w-20')]">
    <div class="w-4 h-4 flex items-center justify-center"
         :class="Sidebar ? 'mr-2' : 'mb-1'">
      <span class="material-icons">face</span>
    </div>
    <p class="whitespace-nowrap"
       :class="Sidebar ? '' : 'text-xs'">
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