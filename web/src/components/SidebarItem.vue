<template>
  <RouterLink :to="path === '/' ? path : '/dashboard/' + path"
               class="flex flex-col w-16 min-h-16 text-center rounded-xl p-2 text-primary-400 truncate">

    <div class="flex items-center justify-center">
      <span class="material-icons">{{ icon }}</span>
    </div>

    <p class="whitespace-nowrap text-xs truncate">
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
    "path"
  ],
  data(){
    return{
      selected: false
    }
  }
}
</script>