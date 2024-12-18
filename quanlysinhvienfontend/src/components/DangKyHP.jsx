import { useState, useEffect } from "react";

export default function DangKyHP() {
  const [hocPhan, setHocPhans] = useState([]);
  const danhSachGiangVien = [
    { giangVienID: 1, tenGiangVien: 'Giảng viên A' },
    { giangVienID: 2, tenGiangVien: 'Giảng viên B' },
    { giangVienID: 3, tenGiangVien: 'Giảng viên C' },
  ];
  const [username, setUsername] = useState("");
  const [selectedGiangVien, setSelectedGiangVien] = useState("");

  const LoadHP = async () => {
    try {
      const response = await fetch("http://localhost:8082/api/hoc_phan/all", {
        method: "GET",
      });
      if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
      const res = await response.json();
      console.log("Dữ liệu từ API:", res);
      setHocPhans(res.data || res); 
    } catch (error) {
      console.error('Lỗi khi lấy danh sách học phần', error);
    }
  };

  const handleDangKy = async (hocPhanID) => {
    const giangVien = selectedGiangVien;
    if (!giangVien) {
      alert("Vui lòng chọn giáo viên trước khi đăng ký.");
      return;
    }

    const response = await fetch(
      `http://localhost:8082/api/dang_ky/add?username=${username}&hocPhanID=${hocPhanID}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ "tenGiaoVien": giangVien })
      }
    );

    const result = await response.json();
    alert(result.message);
  };

  useEffect(() => {
    setUsername(localStorage.getItem('username'));
    LoadHP();
  }, []);

  return (
    <div className="container mt-5 p-4 bg-light shadow-lg rounded">
      <h1 className="text-center mb-4 text-primary">Đăng Ký Học Phần</h1>
      {hocPhan.length === 0 ? (
        <div className="alert alert-warning text-center" role="alert">
          Không có dữ liệu học phần nào được hiển thị.
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped table-bordered table-hover">
            <thead className="table-primary">
              <tr>
                <th>Mã học phần</th>
                <th>Môn học</th>
                <th>Học kì</th>
                <th>Năm học</th>
                <th>Giáo viên</th>
                <th>Hành động</th>
              </tr>
            </thead>
            <tbody>
              {hocPhan.map((hp) => (
                <tr key={hp.hocPhanID}>
                  <td>{hp.hocPhanID}</td>
                  <td>{hp.monHocResponse.tenMonHoc}</td>
                  <td>{hp.hocKy}</td>
                  <td>{hp.namHoc}</td>
                  <td>
                    <select 
                      className="form-select bg-info text-white"
                      onChange={(e) => setSelectedGiangVien(e.target.value)}
                    >
                      <option value="">Chọn Giáo Viên</option>
                      {danhSachGiangVien.map((gv) => (
                        <option key={gv.giangVienID} value={gv.tenGiangVien}>
                          {gv.tenGiangVien}
                        </option>
                      ))}
                    </select>
                  </td>
                  <td>
                    <button 
                      className="btn btn-success w-100" 
                      onClick={() => handleDangKy(hp.hocPhanID)}
                    >
                      Đăng Ký
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
