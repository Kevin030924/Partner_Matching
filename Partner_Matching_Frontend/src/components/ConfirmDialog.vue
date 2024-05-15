<template>
  <div v-if="show" class="modal">
    <div class="modal-content">
      <p>{{ message }}</p>
      <div class="buttons">
        <button @click="handleConfirm">确认</button>
        <button @click="handleCancel">取消</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits} from 'vue';

const props = defineProps({
  show: Boolean,
  message: String
});

const emits = defineEmits(['confirm', 'cancel']);

const handleConfirm = () => {
  emits('confirm');
};

const handleCancel = () => {
  emits('cancel');
};
</script>

<style scoped>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.buttons {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

button {
  margin: 0 10px;
  padding: 8px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #f0f0f0;
}
</style>
