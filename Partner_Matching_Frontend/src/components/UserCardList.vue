<template>
  <van-skeleton title avatar :row="3" :loading="props.loading" v-for="user in props.userList">
    <van-card v-if="user.avatarUrl === 'ikun'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="ikun">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug1'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug1">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug2'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug2">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug3'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug3">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug4'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug4">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug5'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug5">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug6'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug6">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug7'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug7">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug8'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug8">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug9'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug9">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === 'cug10'" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="cug10">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
    <van-card v-if="user.avatarUrl === null" :desc="user.profile" :title="`${user.username}（${user.planetCode}）`" :thumb="user.avatarUrl">
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin-right: 8px; margin-top: 8px">
          {{ tag }}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="showContactInfo(user)">联系我</van-button>
      </template>
    </van-card>
  </van-skeleton>
</template>

<script setup lang="ts">
import {UserType} from "../models/user";
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
import { defineProps } from 'vue';
import { Dialog } from "vant";

interface UserCardListProps {
  loading: boolean;
  userList: UserType[];
}

const props = withDefaults(defineProps<UserCardListProps>(), {
  loading: true,
  // @ts-ignore
  userList: [] as UserType[],
});

const showContactInfo = (user: UserType) => {
  // 确保用户数据包含电话和邮箱信息
  if (user.phone && user.email) {
    const contactInfo = `电话：${user.phone}\n邮箱：${user.email}`;
    // 使用 Dialog 组件显示联系方式
    Dialog.alert({
      title: '联系方式',
      message: contactInfo,
    });
  } else {
    // 如果缺少电话或邮箱信息，显示提示信息
    Dialog.alert({
      title: '提示',
      message: '抱歉，暂无联系方式',
    });
  }
};

</script>

<style scoped>

</style>
