<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="loginUser">
      <label for="email">Email:</label>
      <input type="email" id="email" v-model="login.email" required /><br />
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="login.password" required /><br />
      <button type="submit">Login</button>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
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
    await router.push('/'); // Navigate to dashboard after successful login
  } catch (error) {
    errorMessage.value = 'Invalid email or password';
  }
};
</script>

<style scoped>
.error {
  color: red;
}
</style>
