<template>
  <div class="h-screen flex flex-col relative bg-gray-200 transition-[width] shadow-xl z-50 w-20">
    <div class="flex bg-gray-200 border-b-2 border-gray-300 h-16 items-center px-8 py-4 overflow-hidden gap-0 w-20">

    </div>
    <div class="flex flex-col w-full h-full text-2xl overflow-y-auto overflow-x-hidden" style="scrollbar-width: thin;">
      <SidebarItem icon="question" path="tabelaPerguntas">Perguntas</SidebarItem>
      <SidebarItem icon="icons" path="adicionarCategoria">Categorias</SidebarItem>
      <SidebarItem icon="people-group" path="adicionarEquipas">Equipas</SidebarItem>
      <SidebarItem icon="clock-rotate-left" path="adicionarEquipas">Historico</SidebarItem>
      <SidebarItem icon="gamepad" path="jogo">Jogo</SidebarItem>
    </div>
    <div>
      <SidebarItem class="border-t-2" icon="sign-out-alt" path="/">Sair</SidebarItem>
    </div>
  </div>
</template>

<script>
import SidebarItem from './SidebarItem.vue'

export default {
  name: 'Sidebar',
  components: {
    SidebarItem
  },
  data(){
    return{
      SidebarOpen: true
    }
  }
}
</script>
