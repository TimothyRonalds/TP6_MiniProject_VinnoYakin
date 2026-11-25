import java.io.File

fun main() {
    val file = File("agenda_tugas_tp6.txt")
    val daftar = Penyimpanan.muat(file)
    val service = TugasService(daftar)
    var pilihan: Int

    do {
        println("\n=== AGENDA TUGAS SISWA (TP6) ===")
        println("1. Tambah tugas")
        println("2. Tampilkan tugas")
        println("3. Cari tugas")
        println("4. Ubah status tugas")
        println("5. Hapus tugas (Fitur Baru)")
        println("6. Tampilkan statistik (Fitur Baru)")
        println("7. Simpan data")
        println("0. Keluar")
        print("Pilihan Menu [0-7]: ")
        pilihan = readln().toIntOrNull() ?: -1

        when (pilihan) {
            1 -> {
                print("Masukkan ID Tugas: ")
                val id = readln()
                print("Masukkan Judul Tugas: ")
                val judul = readln()
                if (service.tambah(id, judul)) {
                    println(">> Berhasil: Tugas berhasil ditambahkan!")
                } else {
                    println(">> Gagal: Input tidak boleh kosong atau ID sudah ada!")
                }
            }
            2 -> service.tampilkan()
            3 -> {
                print("Masukkan ID Tugas yang Dicari: ")
                val hasil = service.cari(readln())
                if (hasil != null) {
                    val status = if (hasil.selesai) "Selesai" else "Belum Selesai"
                    println(">> Ditemukan: [${hasil.id}] ${hasil.judul} - Status: $status")
                } else {
                    println(">> Gagal: Tugas tidak ditemukan!")
                }
            }
            4 -> {
                print("Masukkan ID Tugas yang Ingin Diubah Statusnya: ")
                if (service.ubahStatus(readln())) {
                    println(">> Berhasil: Status tugas telah diperbarui!")
                } else {
                    println(">> Gagal: ID tugas tidak ditemukan!")
                }
            }
            5 -> {
                print("Masukkan ID Tugas yang Ingin Dihapus: ")
                if (service.hapus(readln())) {
                    println(">> Berhasil: Tugas telah dihapus!")
                } else {
                    println(">> Gagal: ID tugas tidak ditemukan!")
                }
            }
            6 -> service.tampilkanStatistik()
            7 -> {
                Penyimpanan.simpan(file, daftar)
                println(">> Berhasil: Data berhasil disimpan ke berkas agenda_tugas_tp6.txt")
            }
            0 -> {
                Penyimpanan.simpan(file, daftar)
                println(">> Terima kasih! Program selesai dan data telah diamankan.")
            }
            else -> println(">> Peringatan: Pilihan menu tidak valid, silakan coba lagi.")
        }
    } while (pilihan != 0)
}