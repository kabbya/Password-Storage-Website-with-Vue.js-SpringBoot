<template>

    <div class="list row">
      
      <!-- Search Functionality using Site Name -->
  
      <div class="col-md-7">
        <div class="input-group mb-3">
          <input 
            type="text" 
            class="form-control" 
            placeholder="Search by Site Name"
            v-model="siteName"
          />
          <div class="input-group-append">
            <button class="btn  btn-outline-secondary" type="button" @click="searchSiteName">
              Search
            </button>
          </div>
        </div>
      </div>
  
      <!-- End Search Functionality using Site Name -->
  
      <div class="col-md-6">
        <h4>Passwords List</h4>
        <ul class="list-group">
          <li class="list-group-item"
            v-for="(password, index) in passwords"
            :class="{ active: index == currentIndex }"
            :key="index"
            @click="setActivePassword(password, index)"
          >
            {{ password.siteName }}
          </li>
        </ul>
      </div>
      
      <!-- Show Password on Click -->
    
      <div class="col-md-6">
        <div v-if="currentPassword">
          <h4>Password</h4>
          <!-- <div>
            <label><strong>Site Id:</strong></label> {{ currentPassword.pId}}
          </div> -->
          <div>
            <label><strong>Site Name:</strong></label> {{ currentPassword.siteName }}
          </div>
           <div>
            <label><strong>Description:</strong></label> {{ currentPassword.description }}
          </div>
          
          <div>
            <label><strong>Password: </strong></label> 
            <input
              v-on:focus="$event.target.select()" 
              ref="passwordInput" 
              readonly 
              :value="currentPassword.sitePassword" 
            />
          </div>
  
          <div class="btn-toolbar mt-3">
           <button btn-sm @click="copy" >Copy Password</button>
           <router-link :to="'/update/' + this.currentPassword.pId" class="btn btn-primary mx-3">Edit</router-link>
          </div>
        
        </div>
        <div v-else>
          <!-- <br />
          <p>Please click on a Password...</p> -->
        </div>
      </div>
  
      <!-- End Show Password on Click -->
  
    </div>
</template>
  
  <script>

  import UserService from "../services/user.service";
  
  export default {
    
    name: "passwords",
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }
    },
    data() {
      return {
        passwords: [],
        currentPassword: null,
        currentIndex: -1,
        siteName: ""
      };
    },
    methods: {
      retrievePasswords() {
        UserService.getAllPasswords(this.currentUser.id)
          .then(response => {
            this.passwords = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
      },
  
      refreshList() {
        this.retrievePasswords();
        this.currentPassword = null;
        this.currentIndex = -1;
      },
  
      setActivePassword(password, index) {
        this.currentPassword = password;
        this.currentIndex = password ? index : -1;
      },
      
      searchSiteName() {
        console.log(this.currentUser.id);
        console.log(this.siteName);
        UserService.findBySiteName(this.currentUser.id, this.siteName)
        .then(response => {
          this.passwords = response.data;
          this.setActivePassword(null);
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      },

      copy() {
        this.$refs.passwordInput.focus();
        document.execCommand('copy');
    },  
  
    },
    mounted() {
      this.retrievePasswords();
    }
  };
  </script>
  
  <style>
    .list {
      text-align: left;
      max-width: 750px;
      margin: auto;
    }
    
    body{
        background-color: lightgray;
    }
   
  </style>