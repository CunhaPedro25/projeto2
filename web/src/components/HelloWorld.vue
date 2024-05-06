<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="loginUser">
      <label for="email">Email:</label>
      <input type="email" id="email" v-model="login.email" required><br>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="login.password" required><br>
      <button type="submit">Login</button>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      login: {
        email: '',
        password: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post('http://localhost:8080/api/users/login', null, {
          params: {
            email: this.login.email,
            password: this.login.password
          }
        });
        // Successful login, do something with response
        console.log(response.data);
        this.errorMessage = '';
      } catch (error) {
        this.errorMessage = 'Invalid email or password';
      }
    }
  }
};
</script>

<style scoped>
.error {
  color: red;
}
</style>
