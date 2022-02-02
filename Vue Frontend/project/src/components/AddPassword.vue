<template>
  <div class="submit-form">
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

      <div class="form-group">
        <label for="description">Description</label>
            <input
              class="form-control"
              id="description"
              required
              v-model="password.description"
              name="description"
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
        description: "",
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
        description: this.password.description
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
</style>