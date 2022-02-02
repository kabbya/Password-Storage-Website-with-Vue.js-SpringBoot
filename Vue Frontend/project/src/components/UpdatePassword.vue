<template>
  <div v-if="currentPassword" class="edit-form">
    <h4>Password</h4>
    
    <form>
      <div class="form-group">
        <label for="Site Name">SiteName</label>
        <input type="text" class="form-control" id="SiteName"
          v-model="currentPassword.siteName"
        />
      </div>
      <div class="form-group">
        <label for="Site Password">SitePassword</label>
        <input type="text" class="form-control" id="SitePassword"
          v-model="currentPassword.sitePassword"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description"
          v-model="currentPassword.description"
        />
      </div>
    </form>

    <div class="mt-4">
        <button class="bg-danger mr-2" @click="deletePassword">
            Delete
        </button>

        <button type="submit" class="bg-primary ms-2" @click="updatePassword">
            Update
        </button>
    </div>

    <p>{{ message }}</p>
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