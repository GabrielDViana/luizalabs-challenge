import http from "./http-common";

class NoticeService {
  getAll() {
    return http.get("/notices");
  }

  get(id) {
    return http.get(`/notices/${id}`);
  }

  create(data) {
    return http.post("/notices", data);
  }

  delete(id) {
    return http.delete(`/notices/${id}`);
  }
}

export default new NoticeService();