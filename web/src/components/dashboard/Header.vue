<template>
  <div class="flex w-full justify-between px-6 py-4">
    <h1 class="text-4xl text-primary-500">{{ route.name }}</h1>
    <div class="flex gap-2 items-center">
      <span class="material-icons">notifications</span>
      <div @click="$refs.editUser.show()"
           class="flex gap-1 items-center max-w-48 px-4 py-1 hover:bg-background-700 rounded-xl cursor-pointer truncate"
           :class="{ 'bg-primary-500': isModalOpen }">
        <p class="truncate">{{ user.name }}</p>
        <span class="material-icons">account_circle</span>
      </div>
    </div>
  </div>

  <Modal title="Edit User" ref="editUser" @open="handleModalOpen" @close="handleModalClose">
    <div>
      <div class="flex flex-col gap-4 items-center">

        <div class="flex flex-col gap-2 w-full">
          <label for="name">Name</label>
          <input type="text" id="name" v-model="user.name"/>

          <label for="email">Email</label>
          <input type="email" id="email" v-model="user.email"/>

          <label for="phone">Phone</label>
          <input type="tel" id="phone" v-model="user.phone"/>
        </div>

        <div class="flex flex-col gap-2 w-full">
          <label for="address">Address</label>
          <input type="text" id="address" v-model="user.address" />

          <div class="grid grid-cols-2 grid-flow-row lg:flex gap-2">
            <div class="flex flex-col gap-1">
              <label for="postalCode">Postal Code</label>
              <input type="text" id="postalCode" v-model="user.zipcode.id" @blur="fetchZipcodeDetails"/>
            </div>

            <div class="flex flex-col gap-1">
              <label for="district">District</label>
              <input type="text" id="district" v-model="user.zipcode.district" disabled/>
            </div>

            <div class="flex flex-col gap-1">
              <label for="city">City</label>
              <input type="text" id="city" v-model="user.zipcode.city" disabled/>
            </div>

            <div class="flex flex-col gap-1">
              <label for="locale">Locale</label>
              <input type="text" id="locale" v-model="user.zipcode.locale" disabled/>
            </div>
          </div>
        </div>

        <button @click="updateUser" class="primary">Update</button>
      </div>
    </div>
  </Modal>
</template>

<script setup>
import {ref} from "vue";
import { useRoute } from 'vue-router';
import { useUserStore } from "@/store/userStore";
import geocoding from "@/services/geocoding";
import Modal from "../Modal.vue";
import '@material-design-icons/font';
import auth from "../../services/auth.js";

const route = useRoute();
const user = useUserStore();
const editUser = ref(null);
const isModalOpen = ref(false);

const handleModalOpen = () => {
  isModalOpen.value = true;
};

const handleModalClose = () => {
  isModalOpen.value = false;
};

const fetchZipcodeDetails = async () => {
  const zipcodeDetails = await geocoding.fetchZipcodeDetails(user.zipcode.id);
  if (zipcodeDetails) {
    user.zipcode.district = zipcodeDetails.Distrito;
    user.zipcode.city = zipcodeDetails.Concelho;
    user.zipcode.locale = zipcodeDetails.Localidade;
  }
};

const updateUser = async () => {
  editUser.value.close();
  const ok = await auth.updateUser(user);
  if (ok) {
    user.setUser(user);
  }
};
</script>

<style scoped>
</style>