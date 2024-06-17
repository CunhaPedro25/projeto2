<template>
  <div class="flex justify-between w-full h-fit px-4 py-2">
    <div>
      <p>Search:</p>
      <input type="text" class="bg-background-800"/>
    </div>
  </div>
  <div class="flex h-fit w-full p-4 mb-6 overflow-x-auto">
    <table class="w-full" v-if="invoices.length > 0">
      <thead>
      <tr>
        <th>Project</th>
        <th>Stage</th>
        <th>Issue Date</th>
        <th>Payed</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="invoice in invoices" :key="invoice.id">
        <td>Project {{ invoice.project.id }}</td>
        <td>{{ invoice.stage.name }}</td>
        <td>{{ date.formatDate(invoice.issueDate) }}</td>
        <td>{{ invoice.paid ? "Payed" : "Not Payed" }}</td>
        <td>
          <div class="flex gap-2" v-if="invoice.paid === false">
            <div class="button primary" @click="payInvoice(invoice.id)">
              <span class="material-icons">check</span>
              <p>Pay</p>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

    <p class="text-center text-text-300" v-else>No budgets found...</p>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import data from "../../services/data.js";
import date from "@/utils/date";
import Cookies from "js-cookie";
import '@material-design-icons/font';

const id = Cookies.get("user_id");
const type = Cookies.get("user_type");
let invoices = ref([]);

const fetchInvoices = async () => {
  if (type === "client") {
    invoices.value = await data.getClientInvoices(id);
  }
};

onMounted(async () => {
  await fetchInvoices();
});

const payInvoice = async (id) => {
  await data.payInvoice(id);
  await fetchInvoices();
};
</script>

<style scoped>

</style>