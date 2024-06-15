<template>
  <div class="w-full h-full flex justify-center items-center">
    <form @submit.prevent="loginUser"
          class="w-fit h-fit p-6 px-12 flex flex-col justify-center items-center gap-2 bg-background-800 rounded-2xl text-white">
      <h2>Eco Build</h2>

      <div class="flex flex-col gap-2">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="login.email" required/><br/>
      </div>

      <div class="flex flex-col gap-2">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="login.password" required/><br/>
      </div>

      <button type="submit" class="primary">Login</button>
      <p v-if="errorMessage" class="text-red-600">{{ errorMessage }}</p>

      <div class="mt-2 text-sm flex gap-1">
        <p>Dont have an account?</p>
        <RouterLink to="#" class="underline text-primary-500">Register</RouterLink>
      </div>
    </form>
  </div>

</template>

<script setup>
import {ref} from 'vue';
import {useRouter} from 'vue-router';
import {useUserStore} from '@/store/userStore';

const login = ref({
  email: '',
  password: '',
});
const errorMessage = ref('');

const userStore = useUserStore();
const router = useRouter();

const loginUser = async () => {
  try {
    await userStore.login(login.value);
    errorMessage.value = '';
    await router.push('/dashboard/home'); // Navigate to dashboard after successful login
  } catch (error) {
    errorMessage.value = 'Invalid email or password';
  }
};
</script>

<style scoped>

</style>
