<template>
  <template v-if="user">
    <van-cell title="昵称" is-link to="/user/edit" :value="user.username"  @click="toEdit('username', '昵称', user.username)"/>
    <van-cell title="账号" :value="user.userAccount"/>
    <van-cell title="头像" is-link to="/user/update/head" @click="toUpdateHead('avatarUrl', '图像', user.avatarUrl)">
      <img v-if="user.avatarUrl === 'ikun'" style="height: 48px" :src="ikun"/>
      <img v-if="user.avatarUrl === 'cug1'" style="height: 48px" :src="cug1"/>
      <img v-if="user.avatarUrl === 'cug2'" style="height: 48px" :src="cug2"/>
      <img v-if="user.avatarUrl === 'cug3'" style="height: 48px" :src="cug3"/>
      <img v-if="user.avatarUrl === 'cug4'" style="height: 48px" :src="cug4"/>
      <img v-if="user.avatarUrl === 'cug5'" style="height: 48px" :src="cug5"/>
      <img v-if="user.avatarUrl === 'cug6'" style="height: 48px" :src="cug6"/>
      <img v-if="user.avatarUrl === 'cug7'" style="height: 48px" :src="cug7"/>
      <img v-if="user.avatarUrl === 'cug8'" style="height: 48px" :src="cug8"/>
      <img v-if="user.avatarUrl === 'cug9'" style="height: 48px" :src="cug9"/>
      <img v-if="user.avatarUrl === 'cug10'" style="height: 48px" :src="cug10"/>
    </van-cell>
    <van-cell title="性别" is-link :value="user.gender === 0 ? '男' : '女'" @click="toEdit('gender', '性别', user.gender)"/>
    <van-cell title="电话" is-link to="/user/edit" :value="user.phone" @click="toEdit('phone', '电话', user.phone)"/>
    <van-cell title="邮箱" is-link to="/user/edit" :value="user.email" @click="toEdit('email', '邮箱', user.email)"/>

    <van-cell title="编号" :value="user.planetCode"/>
    <van-cell title="注册时间" :value="user.createTime"/>
  </template>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios";
import {Toast} from "vant";
import {getCurrentUser} from "../services/user";
import ikun from "../assets/ikun.jpg";
import cug1 from "../assets/cug1.jpg";
import cug2 from "../assets/cug2.jpg";
import cug3 from "../assets/cug3.gif";
import cug4 from "../assets/cug4.jpg";
import cug5 from "../assets/cug5.jpg";
import cug6 from "../assets/cug6.jpg";
import cug7 from "../assets/cug7.gif";
import cug8 from "../assets/cug8.jpg";
import cug9 from "../assets/cug9.gif";
import cug10 from "../assets/cug10.jpg";


const user = ref();

onMounted(async () => {
  user.value = await getCurrentUser();
})

const router = useRouter();

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

const toUpdateHead = (headKey: string, headName: string, currentValue: string) => {
  router.push({
    path: '/user/update/head',
    query: {
      headKey,
      headName,
      currentValue,
    }
  })
}

</script>

<style scoped>

</style>