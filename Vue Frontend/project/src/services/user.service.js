
import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/';

class UserService {

  getPublicContent() {
    return axios.get(API_URL + 'all');
  }
  
  // get all password by user

  getAllPasswords(id) {
    return axios.get(API_URL + `passwords/user/${id}`, { headers: authHeader() });
  }

  // create password by user

  createPassword(data, id){
    return axios.post(API_URL + `passwords/${id}`, data, { headers: authHeader() });
  }

  // get password by password id

  getPassword(id) {
    return axios.get(API_URL + `passwords/${id}`, { headers: authHeader() });
  }

  // update password

  updatePassword(id, uid, data) {
    return axios.put(API_URL + `passwords/${uid}/${id}`, data, { headers: authHeader() });
  }

  // delete password

  deletePassword(id) {
    return axios.delete(API_URL + `passwords/${id}`, { headers: authHeader() });
  }

  findBySiteName(id, siteName){
    return axios.get(API_URL + `passwords/user/${id}?siteName=${siteName}`, { headers: authHeader() });
  }
}

export default new UserService();