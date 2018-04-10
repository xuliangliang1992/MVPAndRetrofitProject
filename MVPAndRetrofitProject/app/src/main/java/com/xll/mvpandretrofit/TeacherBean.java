package com.xll.mvpandretrofit;

import java.util.List;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/2/1
 * 邮箱：liangliang.xu1110@gmail.com
 */

public class TeacherBean {
    /**
     * total : 6
     * teachers : [{"id":1,"name":"徐亮亮","sex":"男","age":22,"tel":"15021676796","project":"Android"},{"id":2,"name":"顾拜旦","sex":"男","age":41,"tel":"159232324","project":"数学"},{"id":3,"name":"徐亮亮","sex":"男","age":23,"tel":"15021676796","project":"Android"},{"id":4,"name":"陈光明","sex":"男","age":42,"tel":"150000033","project":"体育"},{"id":5,"name":"xuliang","sex":"??·","age":33,"tel":"15021676796","project":"ios"},{"id":6,"name":"é?·???","sex":"??·","age":125,"tel":"558585858","project":"iOS"}]
     */

    private int total;
    private List<TeachersBean> teachers;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TeachersBean> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeachersBean> teachers) {
        this.teachers = teachers;
    }

    public static class TeachersBean {
        /**
         * id : 1
         * name : 徐亮亮
         * sex : 男
         * age : 22
         * tel : 15021676796
         * project : Android
         */

        private int id;
        private String name;
        private String sex;
        private int age;
        private String tel;
        private String project;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }
    }
}
