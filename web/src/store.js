import { defineStore, createPinia } from 'pinia'

const pinia = createPinia()

export const useCounterStore = defineStore('counter', {
    state: () => {
        return { count: 0 }
    },
    actions: {
        increment() {
            this.count++
        },
    },
})

export default pinia