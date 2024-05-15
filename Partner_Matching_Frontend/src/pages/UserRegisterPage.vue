<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
          v-model="userAccount"
          name="userAccount"
          label="账号"
          placeholder="请输入账号"
          :rules="[{ required: true, message: '请填写账号（不少于四位，不能包含特殊符号）' }]"
      />
      <van-field
          v-model="userPassword"
          type="password"
          name="userPassword"
          label="密码"
          placeholder="请输入密码"
          :rules="[{ required: true, message: '请填写密码' }, { min: 8, message: '密码长度至少为8位' }]"
      />
      <van-field
          v-model="checkPassword"
          type="password"
          name="checkPassword"
          label="确认密码"
          placeholder="请确认密码"
          :rules="[{ required: true, message: '请确认密码' }, { validator: checkPasswordMatch, message: '两次密码输入不一致' }]"
      />
      <van-field
          v-model="planetCode"
          name="planetCode"
          label="编号"
          placeholder="请输入编号"
          :rules="[{ required: true, message: '请填写编号' }, { max: 5, message: '编号长度不能超过5位' }]"
      />
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        注册
      </van-button>
    </div>
  </van-form>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { Toast } from 'vant';
import myAxios from '../plugins/myAxios';

import {useRoute, useRouter} from "vue-router";
const router = useRouter();

const userAccount = ref('');
const userPassword = ref('');
const checkPassword = ref('');
const planetCode = ref('');


const onSubmit = async () => {
  try {
    // 发送注册请求
    const res = await myAxios.post('/user/register', {
      userAccount: userAccount.value,
      userPassword: userPassword.value,
      checkPassword: checkPassword.value,
      planetCode: planetCode.value
    });

    // 注册成功
    if (res.code === 0 && res.data) {
      Toast.success('注册成功');
      // 注册成功后跳转到登录页面
      router.push('/user/login');
    } else {
      Toast.fail('注册失败');
    }
  } catch (error) {
    console.error('注册请求出错:', error);
    Toast.fail('注册请求出错');
  }
};
</script>

<style scoped>

</style>
