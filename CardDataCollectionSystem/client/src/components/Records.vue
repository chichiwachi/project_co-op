<template>
  <div class="purple lighten-5">
    <v-container>
        <v-card class="mx-auto text-center" style="max-width: 700px">
            <v-layout  text-center wrap>
                <v-flex mb-4>
                    <v-toolbar-title class="headline text-uppercase">
                    <div class="purple pa-7 white--text"><h2 class="font-weight-black mb-3">เก็บข้อมูลบัตร</h2></div>
                    </v-toolbar-title>
                </v-flex>
            </v-layout>
            <div v-if="saveUnsuccessful">
              <v-alert outlined dense text type="error" prominent border="left">
                  ข้อมูลไม่ครบถ้วน
              </v-alert>
            </div>

            <div v-if="saveSuccessful">
              <v-alert dense outlined text prominent type="success">บันทึกข้อมูลสำเร็จ</v-alert>
            </div>

            <v-card-text>
              <v-form>
                <v-row>
                    <v-col cols="5">
                        <label class="headline">รหัส</label>
                        <v-text-field label="รหัส" v-model="id"></v-text-field>
                    </v-col>
                
                    <v-col cols="15">
                        <label class="headline">ชื่อ-สกุล</label>
                        <v-text-field label="ชื่อ-สกุล" v-model="name" ></v-text-field>
                    </v-col>
                </v-row>

                <v-row>
                    <v-col cols="15">
                      <label class="headline">สถานที่</label>
                      <v-select label="กรุณาเลือกสถานที่"  v-model="records.location" :items="location"
                          class="mx-auto"
                          style="width: 700px"
                          outlined
                          item-text="location"
                          item-value="id"
                          :rules="[(v) => !!v || 'ยังไม่ได้เลือกข้อมูล']"
                          required>></v-select> 
                    </v-col>
                </v-row>

                <v-row>
                  <v-col cols="20">
                    <v-btn  
                      color="success" 
                      height="40" 
                      width="100" 
                      @click="newRecords" dark>บันทึกข้อมูล
                    </v-btn>
                  </v-col>
                  
                  <v-specer></v-specer>
                  <v-specer></v-specer>

                  <v-col cols="20">
                    <v-btn 
                        color="purple" 
                        height="40" 
                        width="100"   
                        to="/searchrecords"> <span class="white--text">ค้นหา<v-icon dark right>mdi-magnify</v-icon></span>
                    </v-btn>
                </v-col>
                </v-row>
                
              </v-form>
            </v-card-text>
        </v-card>
    </v-container>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  name: "records",
  data() {
    return {
      records: [
        {
          location: ""
        }
        
      ],
      menu1: false,
      id: "",
      name: "",
      location: [],
      saveUnsuccessful: false,
      saveSuccessful: false,
    };
  },

  /* eslint-disable no-console */
  methods: {
    // ดึงข้อมูลสถานที่ใส่ combobox
    getLocatin() {
      http
        .get("/location")
        .then(response => {
          this.location = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function เมื่อกดปุ่ม บันทึก
    newRecords() {
      http
        .post(
          "/records/" +
            this.records.location +
            "/" +
            this.recordDate,
          this.records
        )
        .then(response => {
          console.log(response);
          this.saveSuccessful = true;
          this.saveUnsuccessful = false;
        })
        .catch(e => {
          console.log(e);
          this.saveSuccessful = false;
          this.saveUnsuccessful = true;
        });
      this.submitted = true;
    }
  },
  mounted() {
    this.getLocatin();
  }
};
</script>