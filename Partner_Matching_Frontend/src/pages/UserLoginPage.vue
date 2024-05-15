<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
          v-model="userAccount"
          name="userAccount"
          label="账号"
          placeholder="请输入账号"
          :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
          v-model="userPassword"
          type="password"
          name="userPassword"
          label="密码"
          placeholder="请输入密码"
          :rules="[{ required: true, message: '请填写密码' }]"
      />
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        登录
      </van-button>
      <van-button
          style="margin-top: 10px;"
          round
          block
          type="default"
          @click="goToRegister"
      >
        注册
      </van-button>
    </div>
  </van-form>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import myAxios from "../plugins/myAxios";
import {Toast} from "vant";

const router = useRouter();
const route = useRoute();

const userAccount = ref('');
const userPassword = ref('');

const onSubmit = async () => {
  try {
    // 发送登录请求，获得响应对象
    const res = await myAxios.post('/user/login', {
      userAccount: userAccount.value,
      userPassword: userPassword.value,
    });

    // 在控制台输出响应对象
    console.log(res, '用户登录');

    // 响应数据中的 code 属性用于判断登录是否成功
    if (res.code === 0 && res.data) {
      // 如果 code 为 0，表示登录成功
      Toast.success('登录成功');
      // 跳转到之前的页面
      const redirectUrl = route.query?.redirect as string ?? '/';
      window.location.href = redirectUrl;
    } else {
      // 如果 code 不为 0，表示登录失败
      Toast.fail('登录失败');
    }
  } catch (error) {
    // 捕获异常并输出错误信息
    console.error('登录请求出错:', error);
    Toast.fail('登录请求出错');
  }
};

const goToRegister = () => {
  // 跳转至注册页面，假设注册页面路由为 '/register'
  router.push('/user/register');
};

</script>

<style scoped>

</style>
