<template>
  <div id="app">
    <!-- 路由站位 -->
    <router-view></router-view>
  </div>
</template>

<script>
import { initRouter } from './router/index'
export default {
  name: 'app',
  created() {
    initRouter()
  }
}
</script>

<style>
</style>
