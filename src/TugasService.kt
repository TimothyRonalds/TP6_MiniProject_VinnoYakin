class TugasService(private val daftar: MutableList<Tugas>) {

    fun tambah(id: String, judul: String): Boolean {
        if (id.isBlank() || judul.isBlank()) return false
        if (daftar.any { it.id.equals(id.trim(), ignoreCase = true) }) return false
        daftar.add(Tugas(id.trim(), judul.trim()))
        return true
    }

    fun tampilkan() {
        if (daftar.isEmpty()) {
            println("Belum ada tugas dalam agenda.")
            return
        }
        println("\n--- DAFTAR TUGAS ---")
        for ((indeks, tugas) in daftar.withIndex()) {
            val status = if (tugas.selesai) "[Selesai]" else "[Belum Selesai]"
            println("${indeks + 1}. ID: ${tugas.id} | Judul: ${tugas.judul} | Status: $status")
        }
    }

    fun cari(id: String): Tugas? =
        daftar.find { it.id.equals(id.trim(), ignoreCase = true) }

    fun ubahStatus(id: String): Boolean {
        val tugas = cari(id) ?: return false
        tugas.selesai = !tugas.selesai
        return true
    }

    fun hapus(id: String): Boolean {
        val tugas = cari(id) ?: return false
        return daftar.remove(tugas)
    }

    fun tampilkanStatistik() {
        val total = daftar.size
        val selesai = daftar.count { it.selesai }
        val belum = total - selesai
        println("\n--- STATISTIK TUGAS ---")
        println("Total Tugas   : $total")
        println("Selesai       : $selesai")
        println("Belum Selesai : $belum")
    }
}