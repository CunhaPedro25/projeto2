<template>
  <dialog ref="dialog" class="rounded-lg p-4 bg-background-800 text-white" @click.self="close">
    <div class="flex flex-col gap-6">
      <div class="flex justify-between items-center">
        <h1 class="font-bold text-2xl">{{title}}</h1>
        <div class="button-danger" @click="close()">
          <span class="material-icons">close</span>
        </div>
      </div>

      <slot></slot>
    </div>
  </dialog>
</template>

<script>
export default {
  name: 'Modal',
  props: [
    "title"
  ],
  methods: {
    show(){
      this.$refs.dialog.showModal()
      this.$emit('open');
    },
    close(){
      this.$refs.dialog.close()
      this.$emit('close');
    },
  },
  emits: ['close', 'open']
}
</script>

<style scoped>

</style>