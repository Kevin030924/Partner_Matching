<template>
  <template v-if="user">
    <van-cell title="当前用户" :value="user?.username" />
    <van-cell title="修改信息" is-link to="/user/update" />
    <van-cell title="用户标签" is-link to="/user/tags" />
    <van-cell title="我创建的队伍" is-link to="/user/team/create" />
    <van-cell title="我加入的队伍" is-link to="/user/team/join" />
    <div class="centered-container">
    <van-button
        style="margin-top: 10px;width: 180px;"
        round
        block
        type="danger"
        @click="logout"
    >
      退出登录
    </van-button>
    </div>
  </template>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios";
import {Toast} from "vant";
import {getCurrentUser} from "../services/user";


const user = ref();

const router = useRouter();

onMounted(async () => {
  user.value = await getCurrentUser();
})

const toEdit = (editKey: string, editName: string, currentValue: string) => {
  router.push({
    path: '/user/edit',
    query: {
      editKey,
      editName,
      currentValue,
    }
  })
}

onMounted(async () => {
  user.value = await getCurrentUser();
});

const logout = async () => {
  try {
    const res = await myAxios.post('/user/logout');
    if (res.code === 0 && res.data) {
      // 移除本地用户数据
      user.value = null;
      // 重定向到登录页面
      router.push('/user/login');
      Toast.success('退出登录成功');
    } else {
      Toast.fail('退出登录失败');
    }
  } catch (error) {
    console.error('退出登录请求出错:', error);
    Toast.fail('退出登录请求出错');
  }
};
</script>

<style scoped>
.centered-container {
  display: flex;
  justify-content: center;
}
</style>
