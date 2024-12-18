import { useEffect, useState } from "react";

export default function KetQuaHP() {
    const [users, setUsers] = useState([]);
    const [userID, setUserID] = useState("");
    const [hocki, setHocki] = useState();
    const [GPA, setGPA] = useState();

    const getUsers = async () => {
        try {
            const response = await fetch("http://localhost:8082/api/user/users");
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            const data = await response.json();
            setUsers(data.data);
        } catch (error) {
            console.error('Lỗi khi lấy danh sách user:', error);
        }
    };

    useEffect(() => {
        getUsers();
    }, []);

    const handleSelectChange = async (userID) => {
        if (!userID) return;
        setUserID(userID);
        try {
            const response = await fetch(`http://localhost:8082/api/ket_qua/ket_qua_GPA/${userID}`);
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            const diem = await response.json();
            setGPA(diem.data);
        } catch (error) {
            console.error("Lỗi khi lấy thông tin user:", error);
        }
    };

    const handleSelectChange2 = async (num) => {
        if (!num) return;
        try {
            const response = await fetch(`http://localhost:8082/api/ket_qua/ket_qua_1_hoc_ky/${num}/${userID}`);
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            const data = await response.json();
            setHocki(data.data);
        } catch (error) {
            console.error("Lỗi khi lấy thông tin học kỳ:", error);
        }
    };

    return (
        <div className="container py-5">
            {/* Tiêu đề chính */}
            <div className="text-center mb-5">
                <h1 className="display-4 fw-bold text-primary">Kết quả học phần</h1>
            </div>

            {/* Form chọn User */}
            <div className="d-flex justify-content-center mb-4">
                <div className="p-4 bg-white rounded shadow-sm w-50">
                    <label htmlFor="userSelect" className="form-label fw-bold">Chọn User</label>
                    <select 
                        id="userSelect" 
                        className="form-select" 
                        onChange={(e) => handleSelectChange(e.target.value)} 
                        defaultValue=""
                    >
                        <option value="">-- Chọn User --</option>
                        {users.map((user) => (
                            <option key={user.userID} value={user.userID}>
                                User ID: {user.userID} - User name: {user.username}
                            </option>
                        ))}
                    </select>
                </div>
            </div>

            {/* Thông tin GPA */}
            {GPA && (
                <div className="d-flex justify-content-center mb-4">
                    <div className="p-4 bg-light border border-info rounded w-50">
                        <h3 className="mb-3 text-center text-success">GPA của sinh viên là: <span className="fw-bold">{GPA}</span></h3>
                        <label htmlFor="semesterSelect" className="form-label fw-bold">Chọn Học Kỳ</label>
                        <select 
                            id="semesterSelect" 
                            className="form-select" 
                            onChange={(e) => handleSelectChange2(e.target.value)} 
                            defaultValue=""
                        >
                            <option value="">-- Chọn Học Kỳ --</option>
                            {[1, 2, 3, 4, 5, 6, 7].map((num) => (
                                <option key={num} value={num}>{num}</option>
                            ))}
                        </select>
                    </div>
                </div>
            )}

            {/* Bảng hiển thị kết quả */}
            {hocki && (
                <div className="d-flex justify-content-center mt-4">
                    <div className="bg-white p-4 rounded shadow-sm w-100">
                        <h4 className="text-center mb-4 text-primary">Kết quả học kỳ</h4>
                        <table className="table table-bordered table-hover table-striped">
                            <thead className="table-primary">
                                <tr>
                                    <th className="text-center">Tên Học Phần</th>
                                    <th className="text-center">Tên Giáo Viên</th>
                                    <th className="text-center">TX1</th>
                                    <th className="text-center">TX2</th>
                                    <th className="text-center">Điểm cuối kì</th>
                                    <th className="text-center">Tổng kết</th>
                                </tr>
                            </thead>
                            <tbody>
                                {hocki.map((ket_qua) => (
                                    <tr key={ket_qua.id}>
                                        <td className="text-center">{ket_qua.tenHocPhan}</td>
                                        <td className="text-center">{ket_qua.tenGiaoVien}</td>
                                        <td className="text-center">{ket_qua.tx1}</td>
                                        <td className="text-center">{ket_qua.tx2}</td>
                                        <td className="text-center">{ket_qua.diem}</td>
                                        <td className="text-center fw-bold">{ket_qua.tongKet}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            )}
        </div>
    );
}
