<template>
  <div>

    <!-- 用户标签搜索表单 -->
    <form action="/">
      <div style="padding: 12px; display: flex; align-items: center;">
        <!-- 输入新标签 -->
        <input
            type="text"
            v-model="newTag"
            placeholder="输入新标签"
            style="flex: 1; margin-bottom: 12px; padding: 10px; font-size: 16px;"
            maxlength="10"
        />
        <!-- 确认添加按钮 -->
        <van-button @click="addNewTag" type="primary" style="margin-bottom: 8px; margin-left: 10px;">确认添加</van-button>
      </div>
      <!-- 搜索按钮 -->
      <div style="padding: 12px; display: flex; justify-content: flex-end;">
        <van-button block type="primary" @click="doSearchResult">搜索</van-button>
      </div>
    </form>
    <!-- 已选标签 -->
    <van-divider content-position="left">已选标签</van-divider>
    <div v-if="activeIds.length === 0">请选择标签</div>
    <van-row gutter="16" style="padding: 0 16px">
      <van-col v-for="tag in activeIds" :key="tag">
        <van-tag closeable size="small" type="primary" @close="doClose(tag)">
          {{ tag }}
        </van-tag>
      </van-col>
    </van-row>
    <!-- 选择标签 -->
    <van-divider content-position="left">选择标签</van-divider>
    <van-tree-select
        v-model:active-id="activeIds"
        v-model:main-active-index="activeIndex"
        :items="tagList"
        :loading="false"
    />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from "vue-router";
import { Toast } from "vant";

const router = useRouter();
const newTag = ref('');
const originTagList = [
  {
    text: '身份',
    children: [
      { text: '学生', id: '学生' },
      { text: '打工仔', id: '打工仔' },
    ],
  },
  {
    text: '常见爱好',
    children: [
      { text: '唱歌', id: '唱歌' },
      { text: '跳舞', id: '跳舞' },
      { text: 'rap', id: 'rap' },
      { text: '打篮球', id: '打篮球' },
      { text: 'IT', id: 'IT' },
    ],
  },
]

// 标签列表
const tagList = ref(originTagList);
// 已选中的标签
const activeIds = ref([]);
const activeIndex = ref(0);

// 添加新标签
const addNewTag = () => {
  const tag = newTag.value.trim();
  if (tag) {
    activeIds.value.push(tag); // 添加新标签，text 和 id 值相同
    newTag.value = ''; // 清空输入框
  }
}

// 移除标签
const doClose = (tag) => {
  activeIds.value = activeIds.value.filter(item => {
    return item !== tag;
  })
}

// 执行搜索
const doSearchResult = () => {
  // 将标签列表转换为标签 id 字符串
  const tags = activeIds.value.join(',');
  // 跳转到搜索结果页面，传递标签 id 字符串作为参数
  router.push({
    path: '/user/list',
    query: {
      tags: tags
    }
  })
}

</script>

<style scoped>
/* 样式代码 */
</style>