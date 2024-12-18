import { useEffect, useState } from "react";

export default function LichThi(){
    const [dangKy, setDangKy] = useState([]);
    const [userID, setUserID] = useState(0);
    const [showModal, setShowModal] = useState(false);
    const [dulieu, setDulieu] = useState(null);

    const closeModal = () => {
        setShowModal(false); 
    };

    const loadDangKy = async() => {
        const response = await fetch(`http://localhost:8082/api/dang_ky/all/${userID}`,
           { 
            method: "GET"
           }
        )
        if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
        const res = await response.json();
        console.log("Dữ liệu từ API:", res);
        setDangKy(res.data);
    }
    const handleXem =async (dangkyID) => {
        
        try{
            const response = await fetch(`http://localhost:8082/api/dang_ky/lich_thi/${dangkyID}`,{
            method : "GET"
        });
        const res = await response.json();
        setDulieu(res.data);
        
        if (!response.ok) {alert(res.message);}
        else{
            setShowModal(true);
        }
        
        }catch(err){
            console.error(err);
        }

    }

    useEffect(()=>{
        setUserID(localStorage.getItem('userID'));
        loadDangKy()
    }) 

    return (
        <div className="container mt-5 p-4 bg-light shadow-lg rounded">
      <h1 className="text-center mb-4 text-primary">Xem Lịch Thi</h1>
      {dangKy.length === 0 ? (
        <div className="alert alert-warning text-center" role="alert">
          Không có dữ liệu học phần nào được hiển thị.
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped table-bordered table-hover">
            <thead className="table-primary">
              <tr>
                <th>Mã đăng ký</th>
                <th>Môn học</th>
                <th>Học kì</th>
                <th>Năm học</th>
                <th>Giáo viên</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {dangKy.map((dk) => (
                <tr key={dk.dangKyID}>
                  <td>{dk.dangKyID}</td>
                  <td>{dk.hocPhan.monHocResponse.tenMonHoc}</td>
                  <td>{dk.hocPhan.hocKy}</td>
                  <td>{dk.hocPhan.namHoc}</td>
                  <td>{dk.tenGiaoVien}</td>
                  <td>
                    <button 
                      className="btn btn-success w-100" 
                      onClick={() => handleXem(dk.dangKyID)}
                    >
                      Xem Lịch Thi
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
      {showModal && (
                <div className="modal" style={{ display: "block" }}>
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title">Thông báo</h5>
                                <button type="button" className="btn-close" onClick={closeModal}></button>
                            </div>
                            <div className="modal-body">
                                <p><strong>Thông Lịch Thi</strong> </p>
                                <br />
                                <p><strong>Lịch thi của học phần này</strong></p>
                                <p>{dulieu}</p>
                                
                            </div>
                        </div>
                    </div>
                </div>
            )}
    </div>
    )
} 