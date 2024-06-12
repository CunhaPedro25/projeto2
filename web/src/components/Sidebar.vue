<template>
  <div class="w-1/3 h-screen flex flex-col relative bg-gray-200 transition-[width] shadow-xl z-50"
       :class="SidebarOpen ? 'w-1/3' : 'w-20'">
    <div class="flex bg-gray-200 border-b-2 border-gray-300 h-16 items-center gap-4 px-8 py-4 overflow-hidden"
         :class="SidebarOpen ? 'w-full' : 'gap-0 w-20'">

      <div class="w-4 h-4 flex justify-center cursor-pointer group items-center" @click="SidebarOpen = !SidebarOpen"
           :class="SidebarOpen ? 'mr-2' : ''">
        <div class="absolute w-8 h-8 cursor-pointer left-6 rounded-full group-active:bg-gray-300 transition-colors ease-in-out"></div>
<!--        <faIcon icon="bars" class="z-10" />-->
      </div>
      <template v-if="SidebarOpen">
<!--        <router-link to="/" class="min-w-[6rem] max-w-[6rem] cursor-pointer">-->
<!--          <img class="w-full h-full object-cover" src="@/assets/ConcursoMonserrateSabe.png" alt="Logo do Concurso MonserrateSabe"/>-->
<!--        </router-link>-->
      </template>
    </div>
    <div class="flex flex-col w-full h-full text-2xl overflow-y-auto overflow-x-hidden" style="scrollbar-width: thin;">
      <SidebarItem icon="question" path="tabelaPerguntas" :Sidebar="SidebarOpen" >Perguntas</SidebarItem>
      <SidebarItem icon="icons" path="adicionarCategoria" :Sidebar="SidebarOpen" >Categorias</SidebarItem>
      <SidebarItem icon="people-group" path="adicionarEquipas" :Sidebar="SidebarOpen" >Equipas</SidebarItem>
      <SidebarItem icon="clock-rotate-left" path="adicionarEquipas" :Sidebar="SidebarOpen" >Historico</SidebarItem>
      <SidebarItem icon="gamepad" path="jogo" :Sidebar="SidebarOpen" >Jogo</SidebarItem>
    </div>
    <div>
      <SidebarItem class="border-t-2" icon="sign-out-alt" path="/" :Sidebar="SidebarOpen" >Sair</SidebarItem>
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
