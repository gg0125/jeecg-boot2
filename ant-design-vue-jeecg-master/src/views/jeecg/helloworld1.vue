<template>
  <a-card :bordered="false" style="min-height: 300px">
    <a-button @click="handleClickGetValue">点击获取数据</a-button>
    <j-editable-table
      ref="table1"
      :columns="columns"
      :dataSource="dataSource"
      :action-button="true"
      :rowSelection="true">
      <template v-slot:action="props">
        <a @click="handleDelete(props)">删除</a>
      </template>
    </j-editable-table>
  </a-card>
</template>

<script>
import { FormTypes } from '@/utils/JEditableTableUtil'
import JEditableTable from '@/components/jeecg/JEditableTable'

export default {
  name: 'Example',
  components: {JEditableTable},
  data() {
    return {
      columns: [
        {
          title: '名称',
          key: 'name',
          type:FormTypes.input,
          placeholder:"请输入名称",
          validateRules: [
            {
              required: true,
              message: '姓名必须输入'
            }
          ]
        },
        {
          title: '数字',
          key: 'number',
          type:FormTypes.select,
          placeholder:"请选择数字",
          options: [
            { text: '1', value: '1' },
            { text: '2', value: '2' },
            { text: '3', value: '3' },
            { text: '4', value: '4' },
            { text: '5', value: '5' },
          ]
        },
        {
          title: '日期',
          key: 'date',
          type:FormTypes.date,
        },
        {
          title: '图片',
          key: 'pic',
          type:FormTypes.upload,
          token: true,
          action: ''
        },
        {
          title: '备注',
          key: 'text',
          type:FormTypes.input,
          placeholder:"请输入内容",        },
        {
          title: '操作',
          key: 'action',
          type:FormTypes.slot,
          slot: 'action' //slot名称对应v-slot冒号后面的和等号前面的内容
        },
      ],
      dataSource: []
    }
  },
  methods: {
    handleClickGetValue() {
      let values = this.$refs.table1.getValuesSync()
      console.log('获取到的值',values)
    },
    handleDelete(props){
      console.log('props:',props)
      let { rowId, target } = props
      target.removeRows(rowId)
    }
  }
}
</script>

<style scoped>

</style>