<template>
  <div class="w-full h-full flex justify-center items-center">
    <div class="w-1/2 h-fit p-6 px-12 flex flex-col justify-center items-center gap-2 bg-background-800 rounded-2xl text-white">
      <div class="w-32">
        <img src="@/assets/icons/ecobuild.svg" alt="Logo" />
      </div>


      <div class="flex flex-col gap-2 w-full">
        <label for="name">Name</label>
        <input type="text" id="name" v-model="name"/>

        <div class="flex gap-2 flex-1">
          <div class="flex flex-col flex-1">
            <label for="email">Email</label>
            <input type="email" id="email" class="w-full" v-model="email"/>
          </div>

          <div class="flex flex-col flex-1">
            <label for="email">Password</label>
            <input type="password" id="password" class="w-full" v-model="password"/>
          </div>
        </div>

        <label for="phone">Phone</label>
        <input type="tel" id="phone" v-model="phone"/>
      </div>

      <div class="flex flex-col gap-2 w-full">
        <div class="flex gap-2 flex-1">
          <div class="flex flex-col flex-1">
            <label for="address">Address</label>
            <input type="text" id="address" class="w-full" v-model="address" />
          </div>

          <div class="flex flex-col w-28">
            <label for="door">Door</label>
            <input type="text" id="door" class="w-full" v-model="door" />
          </div>
        </div>

        <div class="grid grid-cols-2 grid-flow-row lg:flex gap-2 flex-1">
          <div class="flex flex-col gap-1 flex-1">
            <label for="postalCode">Postal Code</label>
            <input type="text" id="postalCode" class="w-full"  v-model="zipcode" @blur="fetchZipcodeDetails"/>
          </div>

          <div class="flex flex-col gap-1 flex-1">
            <label for="district">District</label>
            <input type="text" id="district" class="w-full"  v-model="district" disabled/>
          </div>

          <div class="flex flex-col gap-1 flex-1">
            <label for="city">City</label>
            <input type="text" id="city" class="w-full"  v-model="city" disabled/>
          </div>

          <div class="flex flex-col gap-1 flex-1">
            <label for="locale">Locale</label>
            <input type="text" id="locale" class="w-full"  v-model="locale" disabled/>
          </div>
        </div>
      </div>

      <div class="flex gap-2">
        <RouterLink to="/login"  class="button">Back</RouterLink>
        <button @click="registerUser" class="primary">Register</button>
      </div>
    </div>
  </div>
</template>

<script setup>


import {useUserStore} from "../store/userStore.js";
import auth from "../services/auth.js";
import router from "../router.js";
import geocoding from "../services/geocoding.js";
import {ref} from "vue";

let name = ref("")
let email = ref("")
let password = ref("")
let phone = ref("")
let address = ref("")
let door = ref("")
let zipcode = ref("")
let district = ref("")
let city = ref("")
let locale = ref("")

const registerUser = async () => {
  try {
    await auth.register({
      name: name.value,
      email: email.value,
      password: password.value,
      phone: phone.value,
      address: address.value,
      door: door.value,
      zipcode: {
        id: zipcode.value,
        district: district.value,
        city: city.value,
        locale: locale.value,
      },
      userType: {
        id: 1,
        name: 'Client',
      }
    });
    await router.push('/login');
  } catch (error) {
    console.error('Registration failed:', error);
  }
};

const fetchZipcodeDetails = async () => {
  if(zipcode.value === "") return;
  const zipcodeDetails = await geocoding.fetchZipcodeDetails(zipcode.value);
  if (zipcodeDetails) {
    district.value = zipcodeDetails.Distrito;
    city.value = zipcodeDetails.Concelho;
    locale.value = zipcodeDetails.Localidade;
  }
};
</script>

<style scoped>

</style>