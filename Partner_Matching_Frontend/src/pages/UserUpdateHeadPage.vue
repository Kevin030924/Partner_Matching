<template>
  <van-form @submit="onSubmit">
    <van-field
        :placeholder="`这是你现在的头像`"
    />
    <van-card v-if="headUser.currentValue === 'ikun'" :thumb="ikun"></van-card>
    <van-card v-if="headUser.currentValue === 'cug1'" :thumb="cug1"></van-card>
    <van-card v-if="headUser.currentValue === 'cug2'" :thumb="cug2"></van-card>
    <van-card v-if="headUser.currentValue === 'cug3'" :thumb="cug3"></van-card>
    <van-card v-if="headUser.currentValue === 'cug4'" :thumb="cug4"></van-card>
    <van-card v-if="headUser.currentValue === 'cug5'" :thumb="cug5"></van-card>
    <van-card v-if="headUser.currentValue === 'cug6'" :thumb="cug6"></van-card>
    <van-card v-if="headUser.currentValue === 'cug7'" :thumb="cug7"></van-card>
    <van-card v-if="headUser.currentValue === 'cug8'" :thumb="cug8"></van-card>
    <van-card v-if="headUser.currentValue === 'cug9'" :thumb="cug9"></van-card>
    <van-card v-if="headUser.currentValue === 'cug10'" :cug10="ikun"></van-card>
    <van-field
        :placeholder="`选择你需要更换的头像`"
    />
    <van-card :thumb="ikun" @click="onSubmit('ikun')"></van-card>
    <van-card :thumb="cug1" @click="onSubmit('cug1')"></van-card>
    <van-card :thumb="cug2" @click="onSubmit('cug2')"></van-card>
    <van-card :thumb="cug3" @click="onSubmit('cug3')"></van-card>
    <van-card :thumb="cug4" @click="onSubmit('cug4')"></van-card>
    <van-card :thumb="cug5" @click="onSubmit('cug5')"></van-card>
    <van-card :thumb="cug6" @click="onSubmit('cug6')"></van-card>
    <van-card :thumb="cug7" @click="onSubmit('cug7')"></van-card>
    <van-card :thumb="cug8" @click="onSubmit('cug8')"></van-card>
    <van-card :thumb="cug9" @click="onSubmit('cug9')"></van-card>
    <van-card :thumb="cug10" @click="onSubmit('cug10')"></van-card>
  </van-form>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
import { ref } from "vue";
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


const route = useRoute();
const router = useRouter();

const headUser = ref({
  headKey: route.query.headKey,
  currentValue: route.query.currentValue,
  headName: route.query.headName,
})

const onSubmit = async (newValue: string) => {
  const currentUser = await getCurrentUser();

  if (!currentUser) {
    Toast.fail('用户未登录');
    return;
  }

  console.log(currentUser, '当前用户')

  const res = await myAxios.post('/user/update', {
    'id': currentUser.id,
    [headUser.value.headKey as string]: newValue,
  })
  console.log(res, '更新请求');
  if (res.code === 0 && res.data > 0) {
    Toast.success('修改成功');
    router.back();
  } else {
    Toast.fail('修改错误');
  }
};

</script>

<style scoped>

</style>