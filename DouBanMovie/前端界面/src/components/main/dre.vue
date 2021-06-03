<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 基础表格
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <!-- <div class="handle-box">
                <el-input v-model="query.name" placeholder="请输入要搜索的电影名称" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div> -->
            <el-table
                :data="tableData"
                border
                class="table"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column prop="id" label="ID" align="center"></el-table-column>
                <el-table-column prop="movieDirector" label="导演名称" align="center"></el-table-column>
                <el-table-column prop="bad" label="差评数目" align="center"></el-table-column>
                <el-table-column prop="mid" label="中评数目" align="center"></el-table-column>
                <el-table-column prop="good" label="好评数目" align="center"></el-table-column>
                <!-- <el-table-column prop="movieScore" label="电影评分" align="center"></el-table-column>
                <el-table-column prop="moviePerson" label="评分人数" align="center"></el-table-column> -->
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="handleDelete(scope.$index, scope.row)"
                        >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import dre from "@/api/dre";

export default {
    name: "basetable",
    data() {
        return {
            query: {
                name: "",
                pageIndex: 1,
                pageSize: 10
            },
            tableData: [
               
            ],
            pageTotal: 0,
            form: {},
            idx: -1,
            id: -1,
            page:1,
            limit:10,
            movieDirector:{
            // movieName :"你好"
            }
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            dre.getList(this.page,this.limit,this.movieDirector).then(res => {
                console.log('得到的结果:',res);
                this.tableData = res.data.list;
                this.pageTotal = res.data.total || 50;
            });
        },
        // 触发搜索按钮
        handleSearch() {
            this.moviename.movieName = this.query.name
            this.getData()
            // this.$set(this.query, "pageIndex", 1);
            // this.getData();
        },
        // 删除操作
        handleDelete(index) {
            // 二次确认删除
            this.$confirm("确定要删除吗？", "提示", {
                type: "warning"
            })
                .then(() => {
                    this.$message.success("删除成功");
                    this.tableData.splice(index, 1);
                })
                .catch(() => {});
        },
        // 分页导航
        // handlePageChange(val) {
        //     console.log('当前的页数:',this.query.pageIndex)
        //     // this.page = this.query.pageIndex
        //     // console.log('当前的页数123:',this.query.pageIndex)
        //     this.$set(this.query, "pageIndex", val);
        //     this.page = this.query.pageIndex
        //     console.log('this.page:',this.page)
        //     this.getData();
        // }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}
.handle-select {
    width: 120px;
}
.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>