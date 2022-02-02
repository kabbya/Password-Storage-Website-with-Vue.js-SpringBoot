<template>
  <div v-if="currentPassword" class="edit-form">
    <h4>Password</h4>
    
    <form>
      <div class="form-group">
        <label for="Site Name">Name of the Site</label>
        <input type="text" class="form-control" id="SiteName"
          v-model="currentPassword.siteName"
        />
      </div>
      <div class="form-group">
        <label for="siteUserName">User name</label>
        <input type="text" class="form-control" id="siteUserName"
          v-model="currentPassword.siteUserName"
        />
      </div>
      <div class="form-group">
        <label for="Site Password">Password</label>
        <input type="text" class="form-control" id="SitePassword"
          v-model="currentPassword.sitePassword"
        />
      </div>
    </form>

    <div class="mt-4">
        <button class="btn btn-danger mr-2 mb-3" @click="deletePassword">
            Delete
        </button>

        <button type="submit" class="btn btn-primary ms-2 mb-3" @click="updatePassword">
            Update
        </button>
    </div>

    <p>
      <strong> {{ message }} </strong>
    </p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Password...</p>
  </div>
</template>

<script>
import UserService from "../services/user.service";
 
export default {

  name: "password",
  computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }
  },
  data() {
    return {
      currentPassword: null,
      message: ''
    };
  },
  methods: {
    getPassword(pId) {
      UserService.getPassword(pId)
        .then(response => {
          this.currentPassword = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },


    updatePassword() {
      UserService.updatePassword(this.currentPassword.pId, this.currentUser.id,this.currentPassword)
        .then(response => {
          console.log(response.data);
          this.message = 'The password was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deletePassword() {
        
      UserService.deletePassword(this.currentPassword.pId)
        .then(response => {
          console.log(response.data);
          this.message = 'The password has been successfully deleted! Please refresh.';
          this.$router.push("/passwords");
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getPassword(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>