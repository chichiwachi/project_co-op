<template>
  <div class="purple lighten-5">
    <v-container>
        <v-card class="mx-auto text-center" style="max-width: 700px">
          <v-layout  text-center wrap>
                    <v-flex mb-4>
                        <v-toolbar-title class="headline text-uppercase">
                        <div class="purple pa-7 white--text"><h2 class="font-weight-black mb-3">ค้นหาข้อมูล</h2></div>
                        </v-toolbar-title>
                    </v-flex>
                </v-layout>
                
          <v-card-text>
            <v-form>
              <!-- <span class="red--text">หมายเหตุ <span class="black--text">เลือกเพียงหนึ่งหัวข้อ:  รหัส, ชื่อ-สกุล, สถานที่ และช่วงเวลา</span></span> -->
                <v-spacer></v-spacer>
                <v-spacer></v-spacer>
                <v-row justify="center">
                  <v-col cols="5">
                    <label class="headline">รหัส</label>
                    <v-text-field
                      full-width
                      max-width="550px"
                      min-width="550px"
                      outlined
                      label="ค้นหาจากรหัส"
                      v-model="id"
                      required>
                    </v-text-field>
                    <p v-if="idcheck != ''">{{id}}</p>
                  </v-col>

                  <v-col cols="6">
                    <label class="headline">ชื่อ-สกุล</label>
                    <v-text-field
                      full-width
                      max-width="550px"
                      min-width="550px"
                      outlined
                      label="ค้นหาจากชื่อ-สกุล"
                      v-model="name"
                      required>
                    </v-text-field>
                    <p v-if="namecheck != ''">{{name}}</p>
                  </v-col>
                </v-row>

                <v-row>
                    <v-col cols="15">
                      <label class="headline">สถานที่</label>
                      <v-select label="ค้นหาจากสถานที่"  v-model="searchrecords.location" :items="location"
                          class="mx-auto"
                          style="width: 600px"
                          outlined
                          item-text="location"
                          item-value="id"
                          required>></v-select> 
                    </v-col>
                </v-row>

                <label class="headline">ช่วงเวลา</label>
                <v-row justify="center">
                  <v-col cols="5">
                      <v-menu
                          ref="menu1"
                          v-model="menu1"
                          :close-on-content-click="false"
                          transition="scale-transition"
                          offset-y
                          max-width="290px"
                          min-width="290px"
                      >
                      <template v-slot:activator="{ on }">
                          <v-text-field v-model="dateFormatted" label="ค้นหาจากวันที่" @blur="recordDate = parseDate(dateFormatted)" v-on="on"></v-text-field>
                      </template>
                          <v-date-picker v-model="recordDate" no-title @input="menu1 = false"></v-date-picker>
                      </v-menu>
                  </v-col> 

                  <v-col cols="6">
                    <v-select label="ค้นหาจากช่วงเวลา"  v-model="searchrecords.location" :items="time"
                        class="mx-auto"
                        style="width: 400px"
                        outlined
                        item-text="time"
                        item-value="id"
                        required>></v-select> 
                  </v-col>
                </v-row>

                <v-row>
                  <v-col>
                    <div class="my-2">
                      <v-btn @click="findSerialnumber" depressed large color="primary">ค้นหา
                        <v-icon dark right>mdi-magnify</v-icon>
                      </v-btn>
                    </div>
                  </v-col>
                </v-row>
              
                <v-spacer></v-spacer>

                <v-data-table
                  :headers="headers"
                  :items="items"
                  item-key="id"
                  :search="search"
                  
                  class="elevation-1"
                ></v-data-table>
                
                <br />

                <v-col cols="15">
                  <v-btn 
                      color="purple" 
                      height="40" 
                      width="100"   
                      to="/records"> <span class="white--text">เก็บข้อมูลบัตร</span>
                  </v-btn>
                </v-col>
          </v-form>
          </v-card-text>
        </v-card>
    </v-container>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "searchrecords",
  data() {
    return {
      menu1: false,
      search:[],
      searchrecords:[{
        id: "",
        name: "",
        location: "",
        time: "",
      }],
      serialnumbercheck: false,
      headers: [
        { text: "รหัส",value: "records.id"},
        { text: "ชื่อ-สกุล",value: "records.name"},
        { text: "สถานที่",value: "location.location"},
        { text: "วัน-เวลา", value: "time.time" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    findSerialnumber() {
      http
        .get("/findBySerialnumber/" + this.serialnumber)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.checkstatusrepair.serialnumber = response.data.id;
            if(this.serialnumber == response.data.serialnumber){
              this.serialnumbercheck = true;
            } else {
              this.serialnumbercheck = false;
            } 
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
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
    formatDate(recordDate) {
      if (!recordDate) return null;
      const [year, month, day] = recordDate.split("-");
      return `${day}/${month}/${year}`;
    },
    parseDate(recordDate) {
      if (!recordDate) return null;
      const [day, month, year] = recordDate.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    },
 
  },
  mounted() {
    this.getLocation();
  }
};
</script>
