<!-- <template>
  <div ref="elRef" :class="[$attrs.class, prefixCls]" :style="styles"></div>
</template>

<script setup lang="js">

import { defineProps, computed, ref, watch, onMounted, onBeforeUnmount, onActivated } from 'vue';
import { debounce } from 'lodash-es';
import echarts from '@/plugins/echarts';
import 'echarts-wordcloud';
import { propTypes } from '@/utils/propTypes';
import { useAppStore } from '@/store/modules/app';
import { isString } from '@/utils/is';
import { useDesign } from '@/hooks/web/useDesign';

const { getPrefixCls, variables } = useDesign();

const prefixCls = getPrefixCls('echart');

const appStore = useAppStore();

const props = defineProps({
  options: {
    type: Object,
    required: true
  },
  width: {
    type: [Number, String],
    default: ''
  },
  height: {
    type: [Number, String],
    default: '500px'
  }
});

const isDark = computed(() => appStore.getIsDark);

const theme = computed(() => {
  const echartTheme = isDark.value ? true : 'auto';
  return echartTheme;
});

const options = computed(() => {
  return Object.assign({}, props.options, {
    darkMode: theme.value
  });
});

const elRef = ref(null);

let echartRef = null;

const contentEl = ref(null);

const styles = computed(() => {
  const width = isString(props.width) ? props.width : `${props.width}px`;
  const height = isString(props.height) ? props.height : `${props.height}px`;

  return {
    width,
    height
  };
});

const initChart = () => {
  if (elRef.value && props.options) {
    echartRef = echarts.init(elRef.value);
    echartRef?.setOption(options.value);
  }
};

watch(
  () => options.value,
  (options) => {
    if (echartRef) {
      echartRef?.setOption(options);
    }
  },
  {
    deep: true
  }
);

const resizeHandler = debounce(() => {
  if (echartRef) {
    echartRef.resize();
  }
}, 100);

const contentResizeHandler = async (e) => {
  if (e.propertyName === 'width') {
    resizeHandler();
  }
};

onMounted(() => {
  initChart();

  window.addEventListener('resize', resizeHandler);

  contentEl.value = document.getElementsByClassName(`${variables.namespace}-layout-content`)[0];
  if (contentEl.value) {
    contentEl.value.addEventListener('transitionend', contentResizeHandler);
  }
});

onBeforeUnmount(() => {
  window.removeEventListener('resize', resizeHandler);
  if (contentEl.value) {
    contentEl.value.removeEventListener('transitionend', contentResizeHandler);
  }
});

onActivated(() => {
  if (echartRef) {
    echartRef.resize();
  }
});

</script> -->