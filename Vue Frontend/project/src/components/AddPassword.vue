<template>
  <div class="submit-form update-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="siteName">Name of the Site</label>
            <input
              type="text"
              class="form-control"
              id="siteName"
              required
              v-model="password.siteName"
              name="siteName"
            />
      </div>
    

      <div class="form-group">
        <label for="siteUserName">User Name (Email only)</label>
            <input
              class="form-control"
              id="siteUserName"
              required
              v-model="password.siteUserName"
              name="siteUserName"
            />
      </div>

      <div class="form-group">
        <label for="sitePassword">Password</label>
            <input
              type="text"
              class="form-control"
              id="sitePassword"
              required
              v-model="password.sitePassword"
              name="sitePassword"
            />
      </div>

      <button @click="savePassword" class="mt-3 btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <div class="text-center">
        <button class="btn btn-success" @click="newPassword">Add Another Password</button>
      </div>
    </div>
  </div>
</template>

<script>

import UserService from "../services/user.service";
  

export default {
  name: "add-password",
  
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data() {
    return {
      password: {
        pId: null,
        siteName:"",
        sitePassword: "",
        siteUserName: "",
        published: false,
        user: null,
      },
      submitted: false
    };
  },
  methods: {
    savePassword() {
      var data = {
        siteName: this.password.siteName,
        sitePassword: this.password.sitePassword,
        siteUserName: this.password.siteUserName
      };

      UserService.createPassword(data, this.currentUser.id)
        .then(response => {
          this.password.pId = response.data.pId;
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newPassword() {
      this.submitted = false;
      this.password = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}

.update-form{
  background-color: #FDFDFD;
   padding: 1rem;
}
</style>