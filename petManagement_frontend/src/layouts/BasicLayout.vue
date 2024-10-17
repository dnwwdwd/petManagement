<template>
  <a-layout class="layout">
    <a-layout-header :style="{ background: '#fff', display: 'flex', alignItems: 'center', marginBottom: '24px' }">
      <img src="../assets/logo.png" alt="Logo" style="height: 40px; margin-right: 20px" />
      <span style="font-size: 15px; color: #1E90FF">江苏牧职宠物管理系统</span>
      <a-menu
          v-model:selectedKeys="selectedKeys"
          theme="light"
          mode="horizontal"
          :style="{ lineHeight: '64px', flexGrow: 1 }"
      >
        <a-menu-item key="1">
          <router-link to="/">首页</router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <router-link to="/pet/adopt">宠物领养</router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <router-link to="/pet/forum">宠物论坛</router-link>
        </a-menu-item>
        <a-menu-item key="4">
          <router-link to="/pet/donate">宠物捐赠</router-link>
        </a-menu-item>
        <a-menu-item key="5">
          <router-link to="/activity">热门活动</router-link>
        </a-menu-item>
      </a-menu>

      <!-- 头像下拉框，使用 margin-left: auto 推到最右边 -->
      <a-dropdown style="margin-left: auto">
        <a-avatar
            shape="circle"
            src="https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/hm/56e55a4c-90d4-49c7-99e0-54a600f7afdd.jpg"
        />
        <template #overlay>
          <a-menu>
            <a-menu-item>
              <router-link to="/admin/management">后台管理</router-link>
            </a-menu-item>
            <a-menu-item>
              <router-link to="/admin/management">个人设置</router-link>
            </a-menu-item>
            <a-menu-item>
              <router-link to="/admin/management">退出登录</router-link>
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </a-layout-header>

    <a-layout-content style="padding: 0 50px; height: 540px">
      <router-view />
    </a-layout-content>

    <a-layout-footer style="text-align: center">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
  </a-layout>
</template>

<script lang="ts" setup>
import {ref} from 'vue';

const selectedKeys = ref<string[]>(['1']);


</script>

<style scoped>


</style>
