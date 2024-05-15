<template>
  <div id="userTagsPage">
    <div v-if="loading">
      <p>Loading...</p>
    </div>
    <div v-else>
      <h2>用户标签:</h2>
      <ul>
        <li v-for="(tag, index) in tags" :key="index" class="tag-item">
          <span>{{ tag }}</span>
          <button @click="confirmDeleteTag(tag)" class="remove-button">Delete</button>
        </li>
      </ul>
      <div class="input-group">
        <input type="text" v-model="newTag" placeholder="添加新的标签：" class="tag-input">
        <button @click="addTag" class="add-button">Add</button>
      </div>

      <!-- 修改后的标签选择组件 -->
      <div v-for="(category, index) in selectableTags" :key="index">
        <h3>{{ category.text }}</h3>
        <ul>
          <li v-for="(tag, tagIndex) in category.children" :key="tagIndex" class="selectable-tag-item">
            <span @click="addSelectedTag(tag.text)">{{ tag.text }}</span>
          </li>
        </ul>
      </div>
    </div>

    <!-- 添加 ConfirmDialog 组件 -->
    <confirm-dialog
        :show="showConfirmDialog"
        :message="'确认删除该标签吗？'"
        @confirm="handleConfirmDelete"
        @cancel="cancelDelete"
    ></confirm-dialog>
  </div>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import myAxios from "../plugins/myAxios";
import { ref, onMounted } from 'vue';
import ConfirmDialog from '../components/ConfirmDialog.vue';

const router = useRouter();
const tags = ref([]);
const loading = ref(true);
const newTag = ref('');
const showConfirmDialog = ref(false);
let tagToDelete = null;

// 可供选择的标签列表，按照分类整理
const selectableTags = ref([
  {
    text: '身份',
    children: [
      { text: '学生' },
      { text: '打工仔' },
      { text: 'IT大佬' },
      { text: '老板' },
      { text: '混子' },
      { text: '富二代' }
    ]
  },
  {
    text: '爱好',
    children: [
      {text: '唱歌'},
      {text: '跳舞'},
      {text: 'rap'},
      {text: '打篮球'},
      {text: '旅行'},
      {text: '绘画'},
      {text: '游戏'},
      {text: '摄影'}
    ]
  },
  {
    text: 'MBTI',
    children: [
      { text: 'ISFJ' },
      { text: 'ISTJ' },
      { text: 'INFJ' },
      { text: 'INTJ' },
      { text: 'ISTP' },
      { text: 'ISFP' },
      { text: 'INFP' },
      { text: 'INTP' },
      { text: 'ESTP' },
      { text: 'ESFP' },
      { text: 'ENFP' },
      { text: 'ENTP' },
      { text: 'ENFJ' },
      { text: 'ESFJ' },
      { text: 'ENFJ' },
      { text: 'ENTJ' },
    ]
  }
]);

onMounted(async () => {
  try {
    const response = await myAxios.get('/user/tags');
    tags.value = response.data;
  } catch (error) {
    console.error('Error fetching user tags:', error);
  } finally {
    loading.value = false;
  }
});

const removeTag = async (tag) => {
  try {
    const response = await myAxios.post('/user/tags/remove', tag, {
      headers: {
        'Content-Type': 'text/plain' // 指定请求数据的类型为纯文本
      }
    });

    if (response.data === 1) {
      tags.value = tags.value.filter(t => t !== tag);
    }
  } catch (error) {
    console.error('Error removing user tag:', error);
  }
};

const confirmDeleteTag = (tag) => {
  tagToDelete = tag;
  showConfirmDialog.value = true;
};

const handleConfirmDelete = () => {
  if (tagToDelete) {
    removeTag(tagToDelete);
    tagToDelete = null;
  }
  showConfirmDialog.value = false;
};

const cancelDelete = () => {
  showConfirmDialog.value = false;
};

const addTag = async () => {
  try {
    const requestData = newTag.value; // 直接将输入框的值作为请求数据

    const response = await myAxios.post('/user/tags/add', requestData, {
      headers: {
        'Content-Type': 'text/plain' // 指定请求数据的类型为纯文本
      }
    });

    if (response.data === 1) {
      tags.value.push(newTag.value);
      newTag.value = '';
    }
  } catch (error) {
    console.error('Error adding user tag:', error);
  }
};

const addSelectedTag = async (tag) => {
  try {
    const response = await myAxios.post('/user/tags/add', tag, {
      headers: {
        'Content-Type': 'text/plain' // 指定请求数据的类型为纯文本
      }
    });

    if (response.data === 1) {
      tags.value.push(tag);
    }
  } catch (error) {
    console.error('Error adding user tag:', error);
  }
};
</script>

<style scoped>
#userTagsPage {
  margin: 20px;
}

ul {
  list-style: none;
  padding: 0;
}

.tag-item {
  margin-bottom: 10px;
  display: flex;
  align-items: center;
}

.tag-item span {
  padding: 5px 10px;
  background-color: #f0f0f0;
  border-radius: 5px;
}

.remove-button {
  margin-left: auto; /* 水平右对齐 */
  margin-right: 10px; /* 调整间距 */
  padding: 5px 10px;
  background-color: #ff6347;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.input-group {
  margin-top: 20px;
  display: flex;
  justify-content: space-between; /* 将子项分开，确保水平对齐 */
}

.tag-input {
  padding: 8px;
  width: 275px;
  border: 1px solid #ccc;
  border-radius: 5px 0 0 5px;
}

.add-button {
  padding: 8px 15px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  position: relative; /* 使用相对定位 */
  top: -2px; /* 向上移动按钮 */
}

.add-button:hover {
  background-color: #0056b3;
}

.selectable-tag-item {
  display: inline-block;
  margin-right: 10px;
  margin-bottom: 10px;
  cursor: pointer;
}

.selectable-tag-item span {
  padding: 5px 10px;
  background-color: #f0f0f0;
  border-radius: 5px;
}
</style>
