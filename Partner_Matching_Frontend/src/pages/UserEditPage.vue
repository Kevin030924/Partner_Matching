<template>
  <van-form @submit="onSubmit">
    <van-field
        v-model="editUser.currentValue"
        :name="editUser.editKey"
        :label="editUser.editName"
        :placeholder="`请输入${editUser.editName}`"
    >
      <!-- 添加条件判断，仅在编辑性别时显示单选按钮组 -->
      <template #input>
        <template v-if="editUser.editKey === 'gender'">
          <van-radio-group v-model="editUser.currentValue" class="gender-radio-group">
            <van-radio name="男" label="0">男</van-radio>
            <van-radio name="女" label="1">女</van-radio>
          </van-radio-group>
        </template>
        <template v-else>
          <input v-model="editUser.currentValue" type="text" class="van-field__control" />
        </template>
      </template>
    </van-field>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import { ref } from "vue";
import myAxios from "../plugins/myAxios";
import { Toast } from "vant";
import { getCurrentUser } from "../services/user";

const route = useRoute();
const router = useRouter();

const editUser = ref({
  editKey: route.query.editKey,
  currentValue: route.query.currentValue,
  editName: route.query.editName,
});

const onSubmit = async () => {
  const currentUser = await getCurrentUser();

  if (!currentUser) {
    Toast.fail('用户未登录');
    return;
  }

  console.log(currentUser, '当前用户');

  // 如果编辑的是性别，则将当前性别值转换为整数类型
  if (editUser.value.editKey === 'gender') {
    editUser.value.currentValue = editUser.value.currentValue === '男' ? 0 : 1;
  }

  const res = await myAxios.post('/user/update', {
    'id': currentUser.id,
    [editUser.value.editKey as string]: editUser.value.currentValue,
  });

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
.gender-radio-group {
  display: flex;
  gap: 60px; /* 调整单选按钮之间的间距 */
}

.van-field__control {
  border: 1px solid #ccc; /* 灰色边框 */
  border-radius: 10px; /* 圆角 */
  padding: 0 10px 0 10px; /* 调整上下内边距 */
  width: 110%;
  margin-left: -50px;
  box-sizing: border-box;
  font-size: 16px; /* 修改字体大小 */
}
</style>
